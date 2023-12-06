package advent2023

open class Day06 {

    val example = """
        Time:      7  15   30
        Distance:  9  40  200
    """.trimIndent().lines()

    data class Race(
        val time: Long,
        val recordDistance: Long
    )

    fun countWinningTimes(race: Race) = (1..<race.time).count { holdTime ->
        (race.time - holdTime) * holdTime > race.recordDistance
    }

}
