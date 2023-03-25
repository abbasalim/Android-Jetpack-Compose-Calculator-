package com.gnacoding.composecalculator

import ir.wave.composecalculator.CalculatorViewModel
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import java.math.BigDecimal
import java.math.BigInteger
import java.math.RoundingMode

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    private lateinit var calculatorViewModel:CalculatorViewModel

    @Before
    fun setup(){
        calculatorViewModel = CalculatorViewModel()
    }

    @Test
    fun multiplyTwoBigInt(){
        assertEquals(BigDecimal("6000000000000000000896000000000000000.032768"),calculatorViewModel.calculate("3000000000000000000.256×2000000000000000000.128"))
    }

    @Test
    fun sumTwoInt(){
        assertEquals((256).toBigDecimal(),calculatorViewModel.calculate("128+128"))
    }

    @Test
    fun sumTwoBigInt(){
        assertEquals(BigDecimal("5000000000000000000.384"),calculatorViewModel.calculate("3000000000000000000.256+2000000000000000000.128"))
    }

    @Test
    fun multiplyTwoInt(){
        assertEquals(BigDecimal("16384"),calculatorViewModel.calculate("128×128"))
        assertEquals(BigDecimal("16422.42"),calculatorViewModel.calculate("128.1×128.2"))
        assertEquals(BigDecimal("16422.4200"),calculatorViewModel.calculate("128.10×128.20"))
        assertEquals(BigDecimal("16384.00"),calculatorViewModel.calculate("128.0×128.0"))
        assertEquals(BigDecimal("16384.0000000"),calculatorViewModel.calculate("128.000×128.0000"))
    }

    @Test
    fun divTwoInt(){
        assertEquals(BigDecimal("0.3333"),calculatorViewModel.calculate("1÷3"))
        assertEquals(BigDecimal("0.3333"),calculatorViewModel.calculate("1.0÷3"))
        assertEquals(BigDecimal("0.3333"),calculatorViewModel.calculate("1.0÷3.0"))
        assertEquals(BigDecimal("0.3333"),calculatorViewModel.calculate("1÷3.0"))
    }
}