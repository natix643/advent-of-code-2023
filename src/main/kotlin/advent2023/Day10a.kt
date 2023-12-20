package advent2023

object Day10a : Day10() {

    val example1: List<String> = """
        -L|F7
        7S-7|
        L|7||
        -L-J|
        L|-JF
    """.trimIndent().lines()

    val example2: List<String> = """
        7-F7-
        .FJ|7
        SJLL7
        |F--J
        LJ.LJ
    """.trimIndent().lines()

    val lines = Input.day10()
    val maze = Maze(lines)
    val result = maze.runLoop()
}

fun main() {
    Day10a.maze.print(showDistances = true)
    println(Day10a.result)
}
