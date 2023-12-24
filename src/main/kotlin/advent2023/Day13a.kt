package advent2023

object Day13a {

    val example = """
        #.##..##.
        ..#.##.#.
        ##......#
        ##......#
        ..#.##.#.
        ..##..##.
        #.#.##.#.

        #...##..#
        #....#..#
        ..##..###
        #####.##.
        #####.##.
        ..##..###
        #....#..#
    """.trimIndent()

    class Pattern(lines: List<String>) {
        val rows = lines
        val columns = rows[0].indices.map { x ->
            rows.indices.map { y ->
                lines[y][x]
            }.joinToString("")
        }

        fun findSameNeighbors(list: List<String>): List<Pair<Int, Int>> {
            return list.windowed(2).withIndex().mapNotNull { (i, pair) ->
                if (pair[0] == pair[1]) (i to i + 1) else null
            }
        }

        fun isMirrored(list: List<String>, neighbors: Pair<Int, Int>): Boolean {
            val (first, second) = neighbors
            var i = 1

            while (true) {
                val before = list.getOrNull(first - i)
                val after = list.getOrNull(second + i)

                when {
                    before == null || after == null -> return true
                    before != after -> return false
                    else -> i++
                }
            }
        }

        fun evaluate(): Int {
            val rowMirrorPair = findSameNeighbors(rows).find { isMirrored(rows, it) }
            val columnMirrorPair = findSameNeighbors(columns).find { isMirrored(columns, it) }

            return (rowMirrorPair?.second ?: 0) * 100 + (columnMirrorPair?.second ?: 0)
        }

        override fun toString(): String {
            return "Rows:\n\n" + formatRows() + "\n\nColumns:\n\n" + formatColumns()
        }

        fun formatRows() = rows.mapIndexed { i, row ->
            "$i $row $i"
        }.joinToString("\n")

        fun formatColumns() = StringBuilder().apply {
            for (x in columns.indices) {
                append(x)
            }
            append("\n\n")

            for (y in columns[0].indices) {
                for (x in columns.indices) {
                    append(columns[x][y])
                }
                append("\n")
            }

            append("\n")
            for (x in columns.indices) {
                append(x)
            }
        }.toString()
    }

    val patternLines = Input.day13().joinToString("\n")
        .split("\n\n")
        .map { it.lines() }

    val patterns = patternLines.map { Pattern(it) }

    val result = patterns.sumOf {
        it.evaluate()
    }
}

fun main() {
    println(Day13a.result)
}
