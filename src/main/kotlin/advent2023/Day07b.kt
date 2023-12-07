@file:Suppress("UNUSED_DESTRUCTURED_PARAMETER_ENTRY")

package advent2023

import advent2023.Day07.HandType.*

object Day07b : Day07() {

    const val JOKER = 'J'

    val cardStrengths: Map<Char, Int> =
        "J23456789TQKA".toCharArray().withIndex().associate { it.value to it.index }

    fun parseHandType(cards: String): HandType {
        val countsByCard = cards
            .groupBy { it }
            .mapValues { (card, instances) -> instances.size }

        val countsByNormalCard = countsByCard.filterKeys { it != JOKER }.toMutableMap()
        val jokerCount = countsByCard[JOKER] ?: 0

        if (jokerCount == 5) {
            countsByNormalCard[JOKER] = 5
        } else {
            val biggestPile = countsByNormalCard.maxBy { it.value }
            countsByNormalCard[biggestPile.key] = biggestPile.value + jokerCount
        }

        val cardsByCount = countsByNormalCard.toList().groupBy(
            { (card, count) -> count },
            { (card, count) -> card }
        )

        return when {
            5 in cardsByCount -> FIVE_OF_A_KIND
            4 in cardsByCount -> FOUR_OF_A_KIND
            3 in cardsByCount -> if (2 in cardsByCount) FULL_HOUSE else THREE_OF_A_KIND
            2 in cardsByCount -> if (cardsByCount[2]?.size == 2) TWO_PAIR else ONE_PAIR
            else -> HIGH_CARD
        }
    }

    fun parseHand(line: String): Hand {
        val (cards, bid) = line.split(" ")
        return Hand(
            cards = cards.toCharArray().toList(),
            bid = bid.toInt(),
            type = parseHandType(cards)
        )
    }

    val hands = Input.day07().map { parseHand(it) }
    val result = hands
        .sortedWith(handComparator(cardStrengths))
        .mapIndexed { index, card -> (index + 1) * card.bid }
        .sum()
}

fun main() {
    println(Day07b.result)
}
