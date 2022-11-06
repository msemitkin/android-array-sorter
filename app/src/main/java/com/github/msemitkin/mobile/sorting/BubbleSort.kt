package com.github.msemitkin.mobile.sorting

class BubbleSort : SortingStrategy {

    override fun <T : Comparable<T>> sort(items: Collection<T>): SortingResult<T> {
        return bubbleSort(items.toList())
    }

    private fun <T : Comparable<T>> bubbleSort(items: List<T>): SortingResult<T> {
        var iterationsCount = 0
        val itemsToSort = ArrayList(items)
        for (i in 0 until itemsToSort.size) {
            for (j in 0 until itemsToSort.size - 1) {
                if (itemsToSort[j] > itemsToSort[j + 1]) {
                    if (itemsToSort[j] > itemsToSort[j + 1]) {
                        val temp = itemsToSort[j + 1]
                        itemsToSort[j + 1] = itemsToSort[j]
                        itemsToSort[j] = temp
                    }
                }
                iterationsCount++
            }
        }
        return SortingResult(itemsToSort, iterationsCount)
    }
}