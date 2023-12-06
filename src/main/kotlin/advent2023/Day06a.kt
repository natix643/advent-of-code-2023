package advent2023

object Day06a : Day06() {

    fun parseLine(line: String): List<Long> =
        line.split(":")[1].trim().split(Regex("\\s+")).map { it.toLong() }

    val lines = Input.day06()
    val times = parseLine(lines[0])
    val recordDistances = parseLine(lines[1])
    val races = times.zip(recordDistances).map { Race(it.first, it.second) }

    val result = races
        .map { countWinningTimes(it) }
        .reduce { a, b -> a * b }
}

fun main() {
    println(Day06a.result)
}
