package com.github.ivnmrtk.day01

import java.io.File

fun main() {
    var sum: Int = 0
    File("src/main/kotlin/com/github/ivnmrtk/day01/input.txt").forEachLine {
        var isDigitEncountered = false
        val numStringBuilder = StringBuilder()
        var lastEncounteredDigit = ' '
        for ((i, char) in it.toCharArray().withIndex()) {
            if (char.isDigit()) {
                lastEncounteredDigit = char
                if (!isDigitEncountered) {
                    numStringBuilder.append(lastEncounteredDigit)
                    isDigitEncountered = true
                }
            }
            if (i == it.length - 1) {
                numStringBuilder.append(lastEncounteredDigit)
                val currentNumber = numStringBuilder.toString().toInt()
                sum += currentNumber
                numStringBuilder.delete(0, numStringBuilder.length)
                println("For string $it result is $currentNumber, sum is $sum")
                break
            }
        }
    }
    println("Final sum is $sum")
}