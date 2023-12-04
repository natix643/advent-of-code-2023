package advent2023

import kotlin.math.pow

object Day04a : Day04() {

    val result = Input.day04().sumOf { line ->
        val numberPart = line.split(":")[1]
        val (winningNumbers, myNumbers) = numberPart.split("|").map(::parseNumbers)

        val matches = myNumbers.count { it in winningNumbers }
        if (matches > 0) 2.0.pow(matches - 1).toInt() else 0
    }
}

fun main() {
    println(Day04a.result)
}
