package advent2023

object Day02a : Day02() {

    const val MAX_RED = 12
    const val MAX_GREEN = 13
    const val MAX_BLUE = 14

    fun Set.isPossible() = red <= MAX_RED && green <= MAX_GREEN && blue <= MAX_BLUE

    val result = Input.day02()
        .map { parseGame(it) }
        .filter { game -> game.sets.all { it.isPossible() } }
        .sumOf { it.id }
}

fun main() {
    println(Day02a.result)
}
