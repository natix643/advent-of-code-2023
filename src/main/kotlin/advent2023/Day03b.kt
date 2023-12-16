package advent2023

object Day03b : Day03() {

    data class Item(
        val value: String,
        val x: Int,
        val xEnd: Int = x,
        val y: Int,
    )

    val lines = Input.day03()

    val gears = lines.flatMapIndexed { index, line ->
        Regex("\\*").findAll(line).map {
            Item(
                value = it.value,
                x = it.range.first,
                y = index
            )
        }
    }

    val numbers = lines.flatMapIndexed { index, line ->
        Regex("\\d+").findAll(line).map {
            Item(
                value = it.value,
                x = it.range.first,
                xEnd = it.range.last,
                y = index
            )
        }
    }

    fun findNumbersAroundGear(gear: Item): List<Item> {
        return numbers.filter { number ->
            gear.y in (number.y - 1..number.y + 1) &&
                    gear.x in (number.x - 1..number.xEnd + 1)
        }
    }

    val result = gears
        .map { findNumbersAroundGear(it) }
        .filter { it.size == 2 }
        .sumOf { it[0].value.toInt() * it[1].value.toInt() }

}

fun main() {
    println(Day03b.result)
}
