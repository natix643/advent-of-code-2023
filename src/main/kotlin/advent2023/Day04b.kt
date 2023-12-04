package advent2023

import kotlin.math.min

object Day04b : Day04() {

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

    val result = parseCards(Input.day04()).apply { multiplyCards(this) }
        .sumOf { it.copies }

}

fun main() {
    println(Day04b.result)
}
