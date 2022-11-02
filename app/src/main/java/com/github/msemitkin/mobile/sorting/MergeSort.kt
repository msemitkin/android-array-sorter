package com.github.msemitkin.mobile.sorting

class MergeSort : SortingStrategy {
    override fun sort(numbers: Collection<Number>): List<Number> =
        mergeSort(numbers.toList())

    private fun mergeSort(numbers: List<Number>): List<Number> {
        if (numbers.size <= 1) {
            return numbers
        }

        val middle = numbers.size / 2
        val left = numbers.subList(0, middle);
        val right = numbers.subList(middle, numbers.size);

        return merge(mergeSort(left), mergeSort(right))
    }

    private fun merge(left: List<Number>, right: List<Number>): List<Number> {
        var indexLeft = 0
        var indexRight = 0
        val newList: MutableList<Number> = mutableListOf()

        while (indexLeft < left.count() && indexRight < right.count()) {
            if (left[indexLeft].toDouble() <= right[indexRight].toDouble()) {
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