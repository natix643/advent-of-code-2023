package advent2023

open class Day02 {

    val example = """
        Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
        Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
        Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
        Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
        Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
    """.trimIndent()

    data class Game(
        val id: Int,
        val sets: List<Set>
    )

    data class Set(
        var red: Int = 0,
        var green: Int = 0,
        var blue: Int = 0
    )

    fun parseGame(line: String): Game {
        val (idPart, setPart) = line.split(":")
        return Game(
            id = idPart.split(" ")[1].toInt(),
            sets = setPart.split(";").map { parseSet(it) }
        )
    }

    fun parseSet(setString: String): Set {
        val set = Set()

        setString.split(",").map { it.trim() }.forEach { cubeString ->
            val parts = cubeString.split(" ")
            val count = parts[0].toInt()

            when (parts[1]) {
                "red" -> set.red += count
                "green" -> set.green += count
                "blue" -> set.blue += count
            }
        }
        return set
    }

}
