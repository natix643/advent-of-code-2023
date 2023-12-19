package advent2023

object Day10a : Day10() {

    const val START = 'S'

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

    data class Coords(
        val x: Int,
        val y: Int
    ) {
        override fun toString() = "[$x,$y]"
    }

    data class Tile(
        val char: Char,
        val coords: Coords,
        var distance: Int? = null
    ) {
        override fun toString(): String {
            return (if (distance != null) distance.toString().last() else beautify(char)).toString()
        }

        fun neighborCoords(): List<Coords> {
            val (x, y) = coords
            return when (char) {
                'F' -> listOf(Coords(x, y + 1), Coords(x + 1, y))
                '-' -> listOf(Coords(x - 1, y), Coords(x + 1, y))
                '7' -> listOf(Coords(x - 1, y), Coords(x, y + 1))
                '|' -> listOf(Coords(x, y - 1), Coords(x, y + 1))
                'J' -> listOf(Coords(x, y - 1), Coords(x - 1, y))
                'L' -> listOf(Coords(x, y - 1), Coords(x + 1, y))
                'S' -> listOf(Coords(x, y - 1), Coords(x + 1, y), Coords(x, y + 1), Coords(x - 1, y))
                else -> throw Exception("$char")
            }
        }
    }

    class Maze(lines: List<String>) {

        val tiles: List<List<Tile>> = lines.mapIndexed { y, line ->
            line.toCharArray().mapIndexed { x, char ->
                Tile(
                    char = char,
                    coords = Coords(x, y),
                    distance = if (char == START) 0 else null
                )
            }
        }

        val start: Tile = tiles.firstNotNullOf { row ->
            row.find { it.char == START }
        }

        fun getTile(coords: Coords): Tile? {
            return tiles.getOrNull(coords.y)
                ?.getOrNull(coords.x)
                ?.takeIf { it.char != '.' }
        }

        fun startSuccessors(): Pair<Tile, Tile> {
            val neighbors = start.neighborCoords()
                .mapNotNull { getTile(it) }
                .filter { start.coords in it.neighborCoords() }
            return neighbors[0] to neighbors[1]
        }

        fun run(): Int {
            var (s1, s2) = startSuccessors()
            var distance = 1

            while (s1 != s2) {
                s1.distance = distance
                s2.distance = distance
                distance++

                s1 = nextNeighbor(s1)
                s2 = nextNeighbor(s2)
            }
            s1.distance = distance
            s2.distance = distance

            return distance
        }

        fun nextNeighbor(tile: Tile): Tile = tile.neighborCoords()
            .mapNotNull { getTile(it) }
            .find { it.distance == null }!!

        fun print() {
            for (row in tiles) {
                for (tile in row) {
                    print(tile)
                }
                println()
            }
            println()
        }
    }

    val lines = example2
    val maze = Maze(lines)
    val result = maze.run()
}

fun main() {
    Day10a.maze.print()

    println(Day10a.result)
}
