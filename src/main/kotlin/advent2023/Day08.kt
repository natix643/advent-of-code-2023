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

}
