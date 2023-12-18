package advent2023

import java.util.*
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

open class Day11 {

    val example: List<String> = """
        ...#......
        .......#..
        #.........
        ..........
        ......#...
        .#........
        .........#
        ..........
        .......#..
        #...#.....
    """.trimIndent().lines()

    data class Galaxy(
        val x: Int,
        val y: Int
    ) : Comparable<Galaxy> {

        override fun compareTo(other: Galaxy) = compareValuesBy(this, other, { it.x }, { it.y })

        override fun toString() = "[$x,$y]"
    }

    fun parseEmptyRows(lines: List<String>): SortedSet<Int> =
        lines.indices.filter { y ->
            lines[y].all { it == '.' }
        }.toSortedSet()

    fun findEmptyColumns(lines: List<String>): SortedSet<Int> =
        lines[0].indices.filter { x ->
            lines.all { it[x] == '.' }
        }.toSortedSet()

    fun parseGalaxies(lines: List<String>): SortedSet<Galaxy> =
        lines.indices.flatMap { y ->
            lines[y].indices.flatMap { x ->
                val char = lines[y][x]
                if (char == '#') listOf(Galaxy(x, y)) else emptyList()
            }
        }.toSortedSet()

    fun combinePairs(galaxies: Iterable<Galaxy>): List<Pair<Galaxy, Galaxy>> {
        val pairs = mutableListOf<Pair<Galaxy, Galaxy>>()
        for (a in galaxies) {
            for (b in galaxies) {
                if (a < b) {
                    pairs += a to b
                }
            }
        }
        return pairs
    }

    fun distance(
        from: Galaxy,
        to: Galaxy,
        emptyRows: Set<Int>,
        emptyColumns: Set<Int>,
        expansion: Long
    ): Long {
        val deltaX = abs(from.x - to.x) +
                (expansion - 1) * emptyColumns.count { it in (min(from.x, to.x)..max(from.x, to.x)) }
        val deltaY = abs(from.y - to.y) +
                (expansion - 1) * emptyRows.count { it in (min(from.y, to.y)..max(from.y, to.y)) }
        return deltaX + deltaY
    }
}
