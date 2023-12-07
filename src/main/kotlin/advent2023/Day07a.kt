package advent2023

import advent2023.Day07.HandType.*

object Day07a : Day07() {

    val cardStrengths: Map<Char, Int> =
        "23456789TJQKA".toCharArray().withIndex().associate { it.value to it.index }

    fun parseCardCounts(cards: String): Map<Int, List<Char>> {
        val countsByCard = cards
            .groupBy { it }
            .map { (card, instances) -> card to instances.size }
        return countsByCard.groupBy(
            { (_, count) -> count },
            { (card, _) -> card }
        )
    }

    fun parseHandType(cards: String): HandType {
        val cardsByCount = parseCardCounts(cards)
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
    println(Day07a.result)
}
