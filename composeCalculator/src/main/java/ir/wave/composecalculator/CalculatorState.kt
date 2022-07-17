package ir.wave.composecalculator

import ir.wave.composecalculator.Utils.CalculatorOperation

 data class CalculatorState(
    val number1: String = "",
    val number2: String = "",
    val operation: CalculatorOperation? = null
)
