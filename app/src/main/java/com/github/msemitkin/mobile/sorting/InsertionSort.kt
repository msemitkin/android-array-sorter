package com.github.msemitkin.mobile.sorting

class InsertionSort : SortingStrategy {
    override fun <T : Comparable<T>> sort(items: Collection<T>): SortingResult<T> {
        return insertionSort(items.toList())
    }

    private fun <T : Comparable<T>> insertionSort(items: List<T>): SortingResult<T> {
        val itemsToSort = ArrayList(items)
        var iterationsCount = 0
        for (i in 1 until itemsToSort.size) {
            val current = itemsToSort[i]
            var j = i - 1
            while (j >= 0 && itemsToSort[j] > current) {
                itemsToSort[j + 1] = itemsToSort[j]
                j--
                iterationsCount++
            }
            itemsToSort[j + 1] = current
        }
        return SortingResult(itemsToSort, iterationsCount)
    }
}