package com.tmsfalcon.device.calculatorbasic

import org.junit.Assert
import org.junit.Test


class OperationTestCases {

    @Test
    fun additionIsCorrect(){
        var c= 5+3
        Assert.assertEquals("c", 8.0, c.toDouble(), 0.0)
    }

    @Test
    fun subtractionIsCorrect(){
        var c= 10-5
        Assert.assertEquals("c", 5.0, c.toDouble(), 0.0)
    }

    @Test
    fun multiplicatioIsCorrect(){
        var c= 15*2
        Assert.assertEquals("c", 30.0, c.toDouble(), 0.0)
    }

    @Test
    fun divisionIsWrong(){
        var c= 30/3
        Assert.assertEquals("c", 10.0, c.toDouble(), 0.0)
    }



}