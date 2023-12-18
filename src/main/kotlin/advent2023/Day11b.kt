package advent2023

object Day11b : Day11() {

    val lines = Input.day11()

    val emptyRows = parseEmptyRows(lines)
    val emptyColumns = findEmptyColumns(lines)
    val galaxies = parseGalaxies(lines)
    val galaxyPairs = combinePairs(galaxies)

    val result = galaxyPairs.sumOf { (from, to) ->
        distance(
            from = from,
            to = to,
            emptyRows = emptyRows,
            emptyColumns = emptyColumns,
            expansion = 1_000_000
        )
    }
}

fun main() {
    println(Day11b.result)
}
