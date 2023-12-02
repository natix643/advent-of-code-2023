package advent2023

object Day02b : Day02() {

    fun Game.minimumSet() = Set(
        red = sets.maxOf { it.red },
        green = sets.maxOf { it.green },
        blue = sets.maxOf { it.blue }
    )

    val result = Input.day02()
        .map { parseGame(it).minimumSet() }
        .sumOf { it.red * it.green * it.blue }
}

fun main() {
    println(Day02b.result)
}
