package advent2023

open class Day09 {

    val example = """
        0 3 6 9 12 15
        1 3 6 10 15 21
        10 13 16 21 30 45
    """.trimIndent().lines()

    fun parseLine(line: String): List<Int> =
        line.split(" ").map { it.toInt() }

    fun generateTriangle(ints: List<Int>): List<MutableList<Int>> {
        val result = mutableListOf(ints.toMutableList())
        var current = ints

        while (!current.all { it == 0 }) {
            val next = current.windowed(2).map { it[1] - it[0] }
            current = next
            result.add(next.toMutableList())
        }
        return result
    }

}
