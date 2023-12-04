package advent2023

import kotlin.math.min

object Day04b : Day04() {

    data class Card(
        val id: Int,
        val matches: Int,
        var copies: Int = 1
    )

    val cards = Input.day04().mapIndexed { index, line ->
        val numberPart = line.split(":")[1]
        val (winningNumbers, myNumbers) = numberPart.split("|").map(Day04a::parseNumbers)
        Card(
            id = index + 1,
            matches = myNumbers.count { it in winningNumbers }
        )
    }

    fun multiplyCards(cards: List<Card>) {
        for (i in 0..<cards.size - 1) {
            val card = cards[i]

            repeat(card.copies) {
                val from = min(i + 1, cards.size - 1)
                val to = min(i + card.matches, cards.size - 1)
                for (j in from..to) {
                    cards[j].copies++
                }
            }
        }
    }

    val result = cards.apply { multiplyCards(this) }
        .sumOf { it.copies }

}

fun main() {
    println(Day04b.result)
}
