package advent2023

open class Day07 {

    val example = """
        32T3K 765
        T55J5 684
        KK677 28
        KTJJT 220
        QQQJA 483
    """.trimIndent().lines()

    enum class HandType {
        HIGH_CARD,
        ONE_PAIR,
        TWO_PAIR,
        THREE_OF_A_KIND,
        FULL_HOUSE,
        FOUR_OF_A_KIND,
        FIVE_OF_A_KIND
    }

    data class Hand(
        val cards: List<Char>,
        val bid: Int,
        val type: HandType
    )

    fun handComparator(cardStrengths: Map<Char, Int>): Comparator<Hand> {
        val cardSelectors = (0..4).map { index ->
            { hand: Hand -> cardStrengths[hand.cards[index]] }
        }.toTypedArray()
        return compareBy({ it.type }, *cardSelectors)
    }

}
