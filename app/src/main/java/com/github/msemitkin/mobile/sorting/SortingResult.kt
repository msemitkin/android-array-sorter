package com.github.msemitkin.mobile.sorting

data class SortingResult<T : Comparable<T>>(
    val sortedValues: List<T>,
    val numberOfIterations: Int
)