package com.github.msemitkin.mobile.sorting

@FunctionalInterface
interface SortingStrategy {
    fun sort(numbers: Collection<Number>): List<Number>;
}