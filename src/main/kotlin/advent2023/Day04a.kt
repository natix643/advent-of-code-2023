package advent2023

import kotlin.math.pow

object Day04a : Day04() {

    val result = parseCards(Input.day04()).sumOf {
        if (it.matches > 0) 2.0.pow(it.matches - 1).toInt()
        else 0
    }
}

fun main() {
    println(Day04a.result)
}
