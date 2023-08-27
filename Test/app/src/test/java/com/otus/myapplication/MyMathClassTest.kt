package com.otus.myapplication

import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class MyMathClassTest {

    @Test
    fun isAdditionCorrect() {
        assertEquals(4, myMathClass?.sum(2, 2))
    }

    @Test(expected = IllegalArgumentException::class)
    fun isDivisionCorrect() {
        assertEquals(0.0, myMathClass?.divide(4, 0))
    }

    private var myMathClass: MyMathClass? = null

    @Before
    fun setUp() {
        myMathClass = MyMathClass()
    }

    @After
    fun tearDown() {
        myMathClass = null
    }

}
