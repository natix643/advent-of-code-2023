package advent2023

import kotlin.math.max
import kotlin.math.min

object Day08b : Day08() {

    val example: List<String> = """
        LR

        11A = (11B, XXX)
        11B = (XXX, 11Z)
        11Z = (11B, XXX)
        22A = (22B, XXX)
        22B = (22C, 22C)
        22C = (22Z, 22Z)
        22Z = (22B, 22B)
        XXX = (XXX, XXX)
    """.trimIndent().lines()

    fun leastCommonMultiple(x: Long, y: Long): Long {
        val max = max(x, y)
        val min = min(x, y)

        var result = max
        while (result % min != 0L) {
            result += max
        }
        return result
    }

    val lines = Input.day08()
    val instructions = parseInstructions(lines)
    val network = parseNetwork(lines)

    val starts = network.keys.filter { it.endsWith("A") }
    val distances = starts.map { start ->
        navigate(network, instructions, start) { it.endsWith("Z") }
    }

    val result = distances.fold(1L) { acc, i ->
        leastCommonMultiple(acc, i.toLong())
    }
}

fun main() {
    println(Day08b.result)
}
