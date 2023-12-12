package advent2023

object Day08a : Day08() {

    val example1: List<String> = """
        RL

        AAA = (BBB, CCC)
        BBB = (DDD, EEE)
        CCC = (ZZZ, GGG)
        DDD = (DDD, DDD)
        EEE = (EEE, EEE)
        GGG = (GGG, GGG)
        ZZZ = (ZZZ, ZZZ)
    """.trimIndent().lines()

    val example2: List<String> = """
        LLR

        AAA = (BBB, BBB)
        BBB = (AAA, ZZZ)
        ZZZ = (ZZZ, ZZZ)
    """.trimIndent().lines()

    const val START = "AAA"
    const val END = "ZZZ"

    val lines = Input.day08()

    val instructions = sequence {
        while (true) {
            yieldAll(lines[0].toList())
        }
    }

    val network: Map<String, Node> =
        lines.drop(2).map { parseNode(it) }.associateBy { it.name }

    fun navigate(): Int {
        var steps = 0
        var current = network.getValue(START)

        for (instruction in instructions) {
            if (current.name == END) {
                break
            }
            current = when (instruction) {
                'L' -> network.getValue(current.left)
                'R' -> network.getValue(current.right)
                else -> throw Exception("$instruction")
            }
            steps++
        }
        return steps
    }

    val result = navigate()
}

fun main() {
    println(Day08a.result)
}
