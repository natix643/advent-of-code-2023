package advent2023

object Input {

    fun day01() = readLines("Day01.txt")

    private fun readLines(filename: String): List<String> {
        return javaClass.getResourceAsStream(filename)!!.use {
            it.bufferedReader().readLines()
        }
    }

}
