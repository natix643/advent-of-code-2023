package advent2023

object Day15a : Day15() {

    val steps = Input.day15().first().split(',')

    val result = steps.sumOf {
        hash(it)
    }
}

fun main() {
    println(Day15a.result)
}
