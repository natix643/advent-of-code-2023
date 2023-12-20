package advent2023

import advent2023.Day10a.beautify

open class Day10 {

    fun beautify(char: Char): Char = when (char) {
        'F' -> '┌'
        '-' -> '─'
        '7' -> '┐'
        '|' -> '│'
        'J' -> '┘'
        'L' -> '└'
        else -> char
    }

    data class Coords(
        val x: Int,
        val y: Int
    ) {
        override fun toString() = "[$x,$y]"
    }

    data class Tile(
        var char: Char,
        val coords: Coords,
        var distance: Int? = null
    ) {
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
                    coords = Coords(x, y)
                )
            }
        }

        val start: Tile = tiles.firstNotNullOf { row ->
            row.find { it.char == 'S' }
        }

        fun getTile(coords: Coords): Tile? {
            return tiles.getOrNull(coords.y)
                ?.getOrNull(coords.x)
                ?.takeIf { it.char != '.' }
        }

        val startSuccessors: Pair<Tile, Tile>
            get() {
                val neighbors = start.neighborCoords()
                    .mapNotNull { getTile(it) }
                    .filter { start.coords in it.neighborCoords() }
                return neighbors[0] to neighbors[1]
            }

        fun nextNeighbor(tile: Tile): Tile {
            return tile.neighborCoords()
                .mapNotNull { getTile(it) }
                .find { it.distance == null }!!
        }

        fun runLoop(): Int {
            start.distance = 0
            var (path1, path2) = startSuccessors
            var distance = 1

            while (path1 != path2) {
                path1.distance = distance
                path2.distance = distance
                distance++

                path1 = nextNeighbor(path1)
                path2 = nextNeighbor(path2)
            }
            path1.distance = distance
            path2.distance = distance

            return distance
        }

        fun print(beautify: Boolean = true, showDistances: Boolean = false) {
            for (row in tiles) {
                for (tile in row) {
                    val char = when {
                        showDistances && tile.distance != null -> tile.distance.toString().last()
                        beautify -> beautify(tile.char)
                        else -> tile.char
                    }
                    print(char)
                }
                println()
            }
            println()
        }
    }
}
