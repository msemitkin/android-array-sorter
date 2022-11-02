package com.github.msemitkin.mobile.sorting

class QuickSort : SortingStrategy {
    override fun sort(numbers: Collection<Number>): List<Number> {
        return quicksort(numbers.toList())
    }

    private fun quicksort(numbers: List<Number>): List<Number> {
        if (numbers.size <= 1) {
            return numbers
        }
        val pivot = numbers[numbers.size / 2]
        val left = numbers.filter { it.toDouble() < pivot.toDouble() }
        val middle = numbers.filter { it.toDouble() == pivot.toDouble() }
        val right = numbers.filter { it.toDouble() > pivot.toDouble() }
        return quicksort(left) + middle + quicksort(right)
    }

}