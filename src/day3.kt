package dev.schlaubi.aoc2023

private data class Number(val y: Int, val startX: Int, val endX: Int, val value: Int) {
    val width get() = endX - startX + 1
}

private data class ValidNumber(val symbol: Pair<Int, Int>, val number: Number)

private val nonDigitRegex = "\\d+".toRegex()

object Day3 : AoCDay {
    override fun performTask1(input: String): Long = process(input).sumOf { it.number.value }.toLong()

    override fun performTask2(input: String): Long = process(input)
            .groupBy(ValidNumber::symbol)
            .filter { (_, values) -> values.size == 2 }
            .map { (_, values) -> values }
            .sumOf { (first, last) -> first.number.value * last.number.value }
            .toLong()


    private fun process(input: String): List<ValidNumber> {
        val grid = input.lines().reversed().map {
            it.toList()
        }

        val numbers = grid.flatMapIndexed { y: Int, row: List<Char> ->
            val line = row.joinToString("")
            nonDigitRegex.findAll(line).map {
                val value = it.value.toInt()
                Number(y, it.range.first, it.range.last, value)
            }
        }

        return numbers.mapNotNull { grid.anyNeighbourIsntDot(it)?.let { symbol -> ValidNumber(symbol, it) } }
    }
}

private fun List<List<Char>>.anyNeighbourIsntDot(number: Number): Pair<Int, Int>? {
    fun check(x: Int, y: Int): Pair<Int, Int>? {
        val char = getOrNull(y)?.getOrNull(x) ?: return null

        return (x to y).takeIf { char != '.' && !char.isDigit() }
    }

    fun checkWidth(y: Int): Pair<Int, Int>? {
        for (x in 0 until number.width) {
            return check(number.startX + x, y) ?: continue
        }

        return null
    }

    return check(number.endX + 1, number.y) // right
            ?: check(number.startX - 1, number.y) // left 
            ?: check(number.startX - 1, number.y - 1) // bottom left 
            ?: check(number.startX - 1, number.y + 1) // top left 
            ?: check(number.endX + 1, number.y + 1) // top right 
            ?: check(number.endX + 1, number.y - 1) // bottom right
            ?: checkWidth(number.y + 1) // top row
            ?: checkWidth(number.y - 1) // bottom row
}
