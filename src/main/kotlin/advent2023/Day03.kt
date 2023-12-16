package advent2023

open class Day03 {

    val example = """
        467..114..
        ...*......
        ..35..633.
        ......#...
        617*......
        .....+.58.
        ..592.....
        ......755.
        ...$.*....
        .664.598..
    """.trimIndent().lines()

    data class Item(
        val value: String,
        val x: Int,
        val xEnd: Int = x,
        val y: Int,
    )

    fun areAdjacent(number: Item, symbol: Item): Boolean =
        symbol.y in (number.y - 1..number.y + 1) &&
                symbol.x in (number.x - 1..number.xEnd + 1)

    fun findNumbers(lines: List<String>): List<Item> =
        lines.flatMapIndexed { index, line ->
            Regex("\\d+").findAll(line).map {
                Item(
                    value = it.value,
                    x = it.range.first,
                    xEnd = it.range.last,
                    y = index
                )
            }
        }

    fun findSymbols(lines: List<String>): List<Item> =
        lines.flatMapIndexed { index, line ->
            Regex("[^\\d.]").findAll(line).map {
                Item(
                    value = it.value,
                    x = it.range.first,
                    y = index
                )
            }
        }
}
