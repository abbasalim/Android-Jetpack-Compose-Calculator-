package ir.wave.composecalculator

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import ir.wave.composecalculator.Utils.CalculatorAction
import ir.wave.composecalculator.Utils.CalculatorOperation
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

class CalculatorViewModel ( ): ViewModel( ) {

    var state by mutableStateOf(CalculatorState())
        set

    var onResult: (String)->Unit ={}
    var roundResult: Boolean=false

    fun onAction(action: CalculatorAction) {
        when (action) {
            is CalculatorAction.Number -> enterNumber(action.number)
            is CalculatorAction.Decimal -> enterDecimal()
            is CalculatorAction.Clear -> state = CalculatorState()
            is CalculatorAction.Operation -> enterOperation(action.operation)
            is CalculatorAction.Calculate -> performCalculation()
            is CalculatorAction.Delete -> performDeletion()
        }
    }

    private fun performDeletion() {
        when {
            state.number2.isNotBlank() -> state = state.copy(
                number2 = state.number2.dropLast(1)
            )
            state.operation != null -> state = state.copy(
                operation = null
            )
            state.number1.isNotBlank() -> state = state.copy(
                number1 = state.number1.dropLast(1)
            )
        }
    }

    private fun performCalculation() {
        val df = DecimalFormat("#.####", DecimalFormatSymbols.getInstance(Locale.ENGLISH))
        val dfInteger = DecimalFormat("#", DecimalFormatSymbols.getInstance(Locale.ENGLISH))
        val symbol = state.operation?.symbol
        var number1 = state.number1
        var number2 = state.number2
        if (number1.isNotBlank() && number2.isNotBlank()) {
            if (number1.endsWith("%"))
                number1 = "(${number1.replace("%","")}÷100)"

            if (number2.endsWith("%")){
                if (symbol=="+" || symbol=="-")
                    number2 = "(($number1×${number2.replace("%","")})÷100)"
                else  //÷ ×
                    number2 = "(${number2.replace("%","")}÷100)"
            }

            var result = calculate("$number1${symbol}$number2")
            state = state.copy(
                number1 = df.format(result),
                number2 = "",
                operation = null
            )

            onResult(if(roundResult) dfInteger.format(result)else df.format(result))
        }
    }

    private fun enterOperation(operation: CalculatorOperation) {
        if (state.number1.isNotBlank()) {
            state = state.copy(operation = operation)
        }
    }

    private fun enterDecimal() {
        if (state.operation == null && !state.number1.contains(".")
            && state.number1.isNotBlank()
        ) {
            state = state.copy(
                number1 = state.number1 + "."
            )
            return
        }

        if (!state.number2.contains(".") && state.number2.isNotBlank()
        ) {
            state = state.copy(
                number2 = state.number2 + "."
            )
            return
        }
    }

    private fun enterNumber(number: Char) {
        if (state.operation == null) {
            if (state.number1.length >= MAX_NUM_LENGTH) {
                return
            }
            state = state.copy(
                number1 = state.number1 + number
            )
            return
        }
        if (state.number2.length >= MAX_NUM_LENGTH) {
            return
        }
        state = state.copy(
            number2 = state.number2 + number
        )
    }

    companion object {
        private const val MAX_NUM_LENGTH = 16
    }


    fun calculate(str: String): BigDecimal {
        Log.d("Calculator", str);
        return if (str.trim { it <= ' ' }.length == 0) 0.toBigDecimal() else object : Any() {
            var pos = -1
            var ch = 0
            fun nextChar() {
                ch = if (++pos < str.length) str[pos].code else -1
            }

            fun eat(charToEat: Int): Boolean {
                while (ch == ' '.code) nextChar()
                if (ch == charToEat) {
                    nextChar()
                    return true
                }
                return false
            }

            fun parse(): BigDecimal {
                nextChar()
                val x = parseExpression()
                if (pos < str.length) {
                    throw RuntimeException("Unexpected: " + ch.toChar())
                }
                return x
            }

            // Grammar:
            // expression = term | expression `+` term | expression `−` term
            // term = factor | term `×` factor | term `÷` factor
            // factor = `+` factor | `−` factor | `(` expression `)`
            //        | number | functionName factor | factor `^` factor
            fun parseExpression(): BigDecimal {
                var x = parseTerm()
                while (true) {
                    if (eat('+'.code)) x += parseTerm() // addition
                    else if (eat('−'.code) || eat('-'.code)) x -= parseTerm() // subtraction
                    else return x
                }
            }

            fun parseTerm(): BigDecimal {
                var x = parseFactor()
                while (true) {
                    if (eat('×'.code)) x *= parseFactor() // multiplication
                    else if (eat('÷'.code)) x = x.divide(parseFactor(),4, RoundingMode.HALF_EVEN)  // division
                    else return x
                }
            }

            fun parseFactor(): BigDecimal {
                if (eat('+'.code)) return parseFactor() // unary plus
                if (eat('−'.code) || eat('-'.code)) return -parseFactor() // unary minus
                var x: BigDecimal
                val startPos = pos
                if (eat('('.code)) { // parentheses
                    x = parseExpression()
                    eat(')'.code)
                } else if (ch >= '0'.code && ch <= '9'.code || ch == '.'.code) { // numbers
                    while (ch >= '0'.code && ch <= '9'.code || ch == '.'.code) nextChar()
                    x = str.substring(startPos, pos).toBigDecimal()
                } else if (ch >= 'a'.code && ch <= 'z'.code) { // functions
                    while (ch >= 'a'.code && ch <= 'z'.code) nextChar()
                    val func = str.substring(startPos, pos)
                    x = parseFactor()
                    x = when (func) {
                        "sqrt" -> Math.sqrt(x.toDouble()).toBigDecimal()
                        "sin" -> Math.sin(Math.toRadians(x.toDouble())).toBigDecimal()
                        "cos" -> Math.cos(Math.toRadians(x.toDouble())).toBigDecimal()
                        "tan" -> Math.tan(Math.toRadians(x.toDouble())).toBigDecimal()
                        else -> throw RuntimeException("Unknown function: $func")
                    }
                } else {
                    throw RuntimeException("Unexpected: " + ch.toChar())
                }
                if (eat('^'.code)) x = Math.pow(x.toDouble(), parseFactor().toDouble()).toBigDecimal() // exponentiation
                return x
            }
        }.parse()
        //        DecimalFormat df = new DecimalFormat(KEYS.Decimal_3, DecimalFormatSymbols.getInstance(Locale.ENGLISH));
    }

}