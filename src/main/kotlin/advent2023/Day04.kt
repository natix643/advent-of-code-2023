package advent2023

open class Day04 {

    val example = """
        Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
        Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
        Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
        Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
        Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
        Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
    """.trimIndent().lines()

    data class Card(
        val id: Int,
        val matches: Int,
        var copies: Int = 1
    )

    fun parseNumbers(s: String) = s.trim().split(Regex("\\s+")).map { it.toInt() }.toSet()

    fun parseCards(lines: List<String>): List<Card> = lines.mapIndexed { index, line ->
        val numberPart = line.split(":")[1]
        val (winningNumbers, myNumbers) = numberPart.split("|").map(::parseNumbers)
        Card(
            id = index + 1,
            matches = myNumbers.count { it in winningNumbers }
        )
    }
}
