package com.github.msemitkin.mobile.sorting

import java.util.concurrent.atomic.AtomicInteger

class QuickSort : SortingStrategy {
    override fun <T : Comparable<T>> sort(items: Collection<T>): SortingResult<T> {
        val counter = AtomicInteger(0)
        return SortingResult(quicksort(items.toList(), counter), counter.toInt())
    }

    private fun <T : Comparable<T>> quicksort(
        items: List<T>,
        iterationsCount: AtomicInteger
    ): List<T> {
        iterationsCount.incrementAndGet()
        if (items.size <= 1) {
            return items
        }
        val pivot = items[items.size / 2]
        val left = items.filter { it < pivot }
        val middle = items.filter { it == pivot }
        val right = items.filter { it > pivot }
        return quicksort(left, iterationsCount) + middle + quicksort(right, iterationsCount)
    }
}