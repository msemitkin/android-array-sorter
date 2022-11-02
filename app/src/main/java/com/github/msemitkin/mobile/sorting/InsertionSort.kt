package com.github.msemitkin.mobile.sorting

class InsertionSort : SortingStrategy {
    override fun sort(numbers: Collection<Number>): List<Number> {
        return insertionSort(numbers.toList())
    }

    private fun insertionSort(numbers: List<Number>): List<Number> {
        val numbersToSort = ArrayList(numbers)
        for (i in 1 until numbersToSort.size) {
            val current = numbersToSort[i]
            var j = i - 1
            while (j >= 0 && numbersToSort[j].toDouble() > current.toDouble()) {
                numbersToSort[j + 1] = numbersToSort[j]
                j--
            }
            numbersToSort[j + 1] = current
        }
        return numbersToSort
    }
}