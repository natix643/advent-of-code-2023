package advent2023

object Day10b : Day10() {

    val example0: List<String> = """
        .....
        .S-7.
        .|.|.
        .L-J.
        .....
    """.trimIndent().lines()

    val example1: List<String> = """
        ...........
        .S-------7.
        .|F-----7|.
        .||.....||.
        .||.....||.
        .|L-7.F-J|.
        .|..|.|..|.
        .L--J.L--J.
        ...........
    """.trimIndent().lines()

    val example2: List<String> = """
        .F----7F7F7F7F-7....
        .|F--7||||||||FJ....
        .||.FJ||||||||L7....
        FJL7L7LJLJ||LJ.L-7..
        L--J.L7...LJS7F-7L7.
        ....F-J..F7FJ|L7L7L7
        ....L7.F7||L7|.L7L7|
        .....|FJLJ|FJ|F7|.LJ
        ....FJL-7.||.||||...
        ....L---J.LJ.LJLJ...
    """.trimIndent().lines()

    val example3: List<String> = """
        FF7FSF7F7F7F7F7F---7
        L|LJ||||||||||||F--J
        FL-7LJLJ||||||LJL-77
        F--JF--7||LJLJ7F7FJ-
        L---JF-JLJ.||-FJLJJ7
        |F|F-JF---7F7-L7L|7|
        |FFJF7L7F-JF7|JL---7
        7-L-JL7||F7|L7F-7F7|
        L.L7LFJ|||||FJL7||LJ
        L7JLJL-JLJLJL--JLJ.L
    """.trimIndent().lines()

    fun Maze.fillAreas(): Int {
        var insideCount = 0
        var isInside = false

        for (row in tiles) {
            for (tile in row) {
                if (tile.distance != null) {
                    if (tile.char in "|F7S") {
                        isInside = !isInside
                    }
                } else {
                    if (isInside) {
                        insideCount++
                        tile.char = 'I'
                    } else {
                        tile.char = 'O'
                    }
                }
            }
        }
        return insideCount
    }

    val lines = Input.day10()
    val maze = Maze(lines)

    val result = maze.let {
        it.runLoop()
        it.fillAreas()
    }
}

fun main() {
    Day10b.maze.print()
    println(Day10b.result)
}
