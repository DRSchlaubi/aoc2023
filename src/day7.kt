package dev.schlaubi.aoc2023

private enum class PokerCard(private val value: Char) {
    A('A'),
    K('K'),
    Q('Q'),
    J('J'),
    T('T'),
    NINE('9'),
    EIGHT('8'),
    SEVEN('7'),
    SIX('6'),
    FIVE('5'),
    FOUR('4'),
    THREE('3'),
    TWO('2');

    companion object Companion {
        fun fromString(value: Char) = entries.first { it.value == value }
    }
}

private fun List<PokerCard>.compareTo(other: List<PokerCard>, withJoker: Boolean = false): Int {
    val myScore = if(withJoker) jokerScore() else calculateScore()
    val otherScore = if(withJoker) other.jokerScore() else other.calculateScore()
    if (myScore != otherScore) return myScore.compareTo(otherScore)

    for ((index, card) in withIndex()) {
        val otherCard = other[index]
        if (otherCard == card) continue
        if(withJoker) {
            if(card == PokerCard.J) return -1
            if(otherCard == PokerCard.J) return 1
        }
        return otherCard.compareTo(card)
    }
    return 0
}
private fun List<PokerCard>.jokerScore(): Int {
    if (PokerCard.J in this) {
        return PokerCard.entries.maxOf { replaceCard ->
            val withCard = toMutableList()
            withCard.replaceAll {
                if (it == PokerCard.J) replaceCard else it
            }
            withCard.calculateScore()
        }
    }
    return calculateScore()
}

private fun List<PokerCard>.calculateScore(): Int {
    val distinct = distinct()
    if (distinct.size == 1) return 5 // Five of Count
    val numbersByCount = distinct.associateWith { count { card -> card == it } }

    if (numbersByCount.containsValue(4)) return 4 // Four of kind
    if (numbersByCount.containsValue(3)) {
        return if (distinct.size == 2) {
            3 // Full House
        } else {
            2 // Three of kind
        }
    }

    val pairs = numbersByCount.count { (_, value) -> value == 2 }
    if (pairs == 2) return 1
    if (pairs == 1) return 0
    return -1
}

private data class Hand(val cards: List<PokerCard>, val bid: Int)

object Day7 : AoCDay {
    override fun performTask1(input: String): Any = performTask(input, jokers = false)
    override fun performTask2(input: String): Any = performTask(input, jokers = true)
    private fun performTask(input: String, jokers: Boolean): Any {
        val hands = input.lines().map {
            val (cardsRaw, bidRaw) = it.split(' ')

            val cards = cardsRaw.map(PokerCard.Companion::fromString)

            Hand(cards, bidRaw.toInt())
        }

        val rankedHands = hands.sortedWith { o1, o2 -> o1.cards.compareTo(o2.cards, jokers) }

        return rankedHands.mapIndexed { index, (_, bid) -> (index + 1) * bid }.sum()
    }
}