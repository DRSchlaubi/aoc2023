package dev.schlaubi.aoc2023


private val whiteSpace = "\\s+".toRegex()

private data class Card(val id: Int, val myNumbers: List<Int>, val winningNumbers: List<Int>) {
    val myWinningNumbers = myNumbers.filter { number -> number in winningNumbers }
    val score: Int
        get() {
            if (myWinningNumbers.isEmpty()) return 0
            return myWinningNumbers.drop(1).fold(1) { acc, _ -> acc * 2 }.toInt()
        }
}

object Day4 : AoCDay {
    override fun performTask1(input: String): Int = process(input).sumOf(Card::score)

    override fun performTask2(input: String): Int {
        val cards = process(input)
        val copies = cards.associateTo(HashMap()) { it.id to 1 }
        for (card in cards) {
            val currentCopies = copies.getValue(card.id)
            repeat(card.myWinningNumbers.size) { i ->
                val nextId = card.id + i + 1
                copies[nextId] = copies.getValue(nextId) + currentCopies
            }
        }
        return copies.values.sum()
    }

    private fun process(input: String) = input.lines().mapIndexed { index, it ->
        val (winning, mine) = it.split("|").map(String::trim)

        val winningNumbers = winning.substringAfter(": ").split(whiteSpace).filterNot(String::isBlank).map(String::toInt)
        val myNumbers = mine.split(whiteSpace).filterNot(String::isBlank).map(String::toInt)
        
        Card(index + 1, myNumbers, winningNumbers)
    }
}
