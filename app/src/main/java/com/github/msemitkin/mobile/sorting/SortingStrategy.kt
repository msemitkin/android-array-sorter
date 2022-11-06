package com.github.msemitkin.mobile.sorting

@FunctionalInterface
interface SortingStrategy {
    fun <T : Comparable<T>> sort(items: Collection<T>): SortingResult<T>
}