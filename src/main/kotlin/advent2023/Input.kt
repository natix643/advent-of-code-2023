package advent2023

object Input {

    fun day01() = readLines("Day01.txt")
    fun day02() = readLines("Day02.txt")
    fun day03() = readLines("Day03.txt")
    fun day04() = readLines("Day04.txt")

    private fun readLines(filename: String): List<String> {
        return javaClass.getResourceAsStream(filename)!!.bufferedReader().use {
            it.readLines()
        }
    }

}
