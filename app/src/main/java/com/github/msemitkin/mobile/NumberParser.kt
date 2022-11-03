package com.github.msemitkin.mobile

fun parseNumbers(textState: String, separator: String): List<Number> =
    textState.trim()
        .split(separator)
        .filter(String::isNotBlank)
        .map { it.toInt() }