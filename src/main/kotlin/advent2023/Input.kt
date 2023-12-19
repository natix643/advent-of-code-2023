package advent2023

object Input {

    fun day01() = readLines("Day01.txt")
    fun day02() = readLines("Day02.txt")
    fun day03() = readLines("Day03.txt")
    fun day04() = readLines("Day04.txt")
    fun day05() = readLines("Day05.txt")
    fun day06() = readLines("Day06.txt")
    fun day07() = readLines("Day07.txt")
    fun day08() = readLines("Day08.txt")
    fun day09() = readLines("Day09.txt")
    fun day10() = readLines("Day10.txt")
    fun day11() = readLines("Day11.txt")
    fun day15() = readLines("Day15.txt")

    private fun readLines(filename: String): List<String> {
        return javaClass.getResourceAsStream(filename)!!.bufferedReader().use {
            it.readLines()
        }
    }

}
