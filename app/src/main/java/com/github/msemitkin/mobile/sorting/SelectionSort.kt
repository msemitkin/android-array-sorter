package com.github.msemitkin.mobile.sorting

class SelectionSort : SortingStrategy {
    override fun <T : Comparable<T>> sort(items: Collection<T>): SortingResult<T> {
        return selectionSort(items.toList())
    }

    private fun <T : Comparable<T>> selectionSort(numbers: List<T>): SortingResult<T> {
        var iterationsCount = 0
        val itemsToSort = ArrayList(numbers)
        for (i in 0 until itemsToSort.size) {
            var min = i
            for (j in i + 1 until itemsToSort.size) {
                if (itemsToSort[j] < itemsToSort[min]) {
                    min = j
                }
                iterationsCount++
            }
            val temp = itemsToSort[min]
            itemsToSort[min] = itemsToSort[i]
            itemsToSort[i] = temp
        }
        return SortingResult(itemsToSort, iterationsCount)
    }
}