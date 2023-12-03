package com.github.ivnmrtk.day01

import java.io.File
import java.util.*
import java.util.regex.Pattern

fun main() {
    val inputFile = File("src/main/kotlin/com/github/ivnmrtk/day01/input.txt")
    val firstSum = getSumOnlyByDigits(inputFile)
    println("I approach sum is $firstSum\n")
    val secondSum = getActualSum(inputFile)
    println("II approach sum is $secondSum")
}

fun getSumOnlyByDigits(file: File): Int {
    println("I approach calculation started")
    var sum = 0
    file.forEachLine {
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
    return sum
}

fun getActualSum(file: File): Int {
    println("II approach calculation started")
    var sum = 0
    file.forEachLine { line ->
        val regex = "(?=([0-9]|one|two|three|four|five|six|seven|eight|nine))."
        val digitsWordsDict = mapOf(
            "one" to "1",
            "two" to "2",
            "three" to "3",
            "four" to "4",
            "five" to "5",
            "six" to "6",
            "seven" to "7",
            "eight" to "8",
            "nine" to "9"
        )
        val digitToIndex = TreeMap<Int, String>()
        val pattern = Pattern.compile(regex)
        val matcher = pattern.matcher(line)
        while (matcher.find()) {
            val key = matcher.start()
            val value = matcher.group(1).let {
                return@let if (it.matches("\\d".toRegex())) {
                    it
                } else {
                    digitsWordsDict[it]
                }
            }
            digitToIndex[key] = value!!
        }
        val extractedNumber = "${digitToIndex.firstEntry().value}${digitToIndex.lastEntry().value}".toInt()
        sum += extractedNumber
        println("For string $line result is $extractedNumber, sum is $sum")
    }
    return sum
}