package com.github.msemitkin.mobile.sorting

import org.junit.Assert.assertEquals
import org.junit.Test

internal class MergeSortTest {

    @Test
    fun sort_successFlow() {
        val numbers = listOf(1, 3, 2, 5, 4)

        val sortedNumbers = MergeSort().sort(numbers).sortedValues

        assertEquals(listOf(1, 2, 3, 4, 5), sortedNumbers)
    }

    @Test
    fun sort_shouldReturnEmptyList_whenGivenEmptyList() {
        val numbers = emptyList<Int>()

        val sortedNumbers = MergeSort().sort(numbers).sortedValues

        assertEquals(emptyList<Number>(), sortedNumbers)
    }

    @Test
    fun sort_shouldSortNumbers_whenGivenIsReverseOrder() {
        val numbers = listOf(5, 4, 3, 2, 1)

        val sortedNumbers = MergeSort().sort(numbers).sortedValues

        assertEquals(listOf(1, 2, 3, 4, 5), sortedNumbers)
    }


}