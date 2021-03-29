package com.acronym.app.utils

import com.acronym.app.model.AcronymResponse
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class AcronymUtilTest {

    @Test
    fun isValidAcronymToSearch() {
        val result = AcronymUtil.isAcronymValidToSearch("WHO")

        assertEquals(true, result)
    }

    @Test
    fun isListNotEmpty() {
        val result = AcronymUtil.isListNotEmpty(listOf(AcronymResponse()))

        assertEquals(true, result)
    }
}