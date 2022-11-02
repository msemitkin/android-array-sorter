package com.github.msemitkin.mobile.sorting

class SelectionSort : SortingStrategy {
    override fun sort(numbers: Collection<Number>): List<Number> {
        return selectionSort(numbers.toList())
    }

    private fun selectionSort(numbers: List<Number>): List<Number> {
        val numbersToSort = ArrayList(numbers)
        for (i in 0 until numbersToSort.size) {
            var min = i
            for (j in i + 1 until numbersToSort.size) {
                if (numbersToSort[j].toDouble() < numbersToSort[min].toDouble()) {
                    min = j
                }
            }
            val temp = numbersToSort[min]
            numbersToSort[min] = numbersToSort[i]
            numbersToSort[i] = temp
        }
        return numbersToSort
    }
}