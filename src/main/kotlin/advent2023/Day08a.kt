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

    val lines = Input.day08()
    val instructions = parseInstructions(lines)
    val network = parseNetwork(lines)

    val result = navigate(network, instructions, "AAA") { it == "ZZZ" }
}

fun main() {
    println(Day08a.result)
}
