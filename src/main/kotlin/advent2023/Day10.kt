package advent2023

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


}
