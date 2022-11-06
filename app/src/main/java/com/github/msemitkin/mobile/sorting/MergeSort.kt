package com.github.msemitkin.mobile.sorting

import java.util.concurrent.atomic.AtomicInteger

class MergeSort : SortingStrategy {
    override fun <T : Comparable<T>> sort(items: Collection<T>): SortingResult<T> {
        val iterationsCounter = AtomicInteger(0)
        return SortingResult(
            mergeSort(items.toList(), iterationsCounter),
            iterationsCounter.toInt()
        )
    }


    private fun <T : Comparable<T>> mergeSort(
        numbers: List<T>,
        iterationsCount: AtomicInteger
    ): List<T> {
        if (numbers.size <= 1) {
            return numbers
        }

        iterationsCount.incrementAndGet()

        val middle = numbers.size / 2
        val left = numbers.subList(0, middle);
        val right = numbers.subList(middle, numbers.size);

        return merge(mergeSort(left, iterationsCount), mergeSort(right, iterationsCount))
    }

    private fun <T : Comparable<T>> merge(left: List<T>, right: List<T>): List<T> {
        var indexLeft = 0
        var indexRight = 0
        val newList: MutableList<T> = mutableListOf()

        while (indexLeft < left.count() && indexRight < right.count()) {
            if (left[indexLeft] <= right[indexRight]) {
                newList.add(left[indexLeft])
                indexLeft++
            } else {
                newList.add(right[indexRight])
                indexRight++
            }
        }

        while (indexLeft < left.size) {
            newList.add(left[indexLeft])
            indexLeft++
        }

        while (indexRight < right.size) {
            newList.add(right[indexRight])
            indexRight++
        }
        return newList;
    }
}