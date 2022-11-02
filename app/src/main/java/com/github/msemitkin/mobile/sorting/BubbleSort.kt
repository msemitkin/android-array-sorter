package com.github.msemitkin.mobile.sorting

class BubbleSort : SortingStrategy {
    override fun sort(numbers: Collection<Number>): List<Number> {
        return bubbleSort(numbers.toList())
    }

    private fun bubbleSort(numbers: List<Number>): List<Number> {
        val numbersToSort = ArrayList(numbers)
        for (i in 0 until numbersToSort.size) {
            for (j in 0 until numbersToSort.size - 1) {
                if (numbersToSort[j].toDouble() > numbersToSort[j + 1].toDouble()) {
                    val temp = numbersToSort[j + 1]
                    numbersToSort[j + 1] = numbersToSort[j]
                    numbersToSort[j] = temp
                }
            }
        }
        return numbersToSort
    }
}