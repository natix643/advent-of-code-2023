package advent2023

open class Day08 {

    data class Node(
        val name: String,
        val left: String,
        val right: String
    )

    fun parseNode(line: String): Node {
        val (name, rest) = line.split(" = ")
        val (left, right) = rest.trimStart('(').trimEnd(')').split(", ")
        return Node(name, left, right)
    }

    fun parseNetwork(lines: List<String>): Map<String, Node> =
        lines.drop(2)
            .map { parseNode(it) }
            .associateBy { it.name }

    fun parseInstructions(lines: List<String>): Sequence<Char> = sequence {
        while (true) {
            yieldAll(lines[0].toList())
        }
    }

    fun navigate(
        network: Map<String, Node>,
        instructions: Sequence<Char>,
        start: String,
        isEnd: (String) -> Boolean
    ): Int {
        var steps = 0
        var current = network.getValue(start)

        for (instruction in instructions) {
            if (isEnd(current.name)) {
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

}
