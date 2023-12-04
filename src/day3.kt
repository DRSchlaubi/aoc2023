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

        return numbers.mapNotNull {
            val symbol = it.adjecentPoints.firstOrNull { (x, y) ->
                val point = grid.getOrNull(y)?.getOrNull(x) ?: return@firstOrNull false
                point != '.' && !point.isDigit()
            } ?: return@mapNotNull null

            ValidNumber(symbol, it)
        }
    }
}

private val Number.adjecentPoints: Sequence<Pair<Int, Int>>
    get() = sequence {
        yield(startX - 1 to y)
        yield(endX + 1 to y)
        // -1 to +1 is for diagonal matches
        for (x in -1 until width + 1) {
            yield(startX + x to y - 1)
            yield(startX + x to y + 1)
        }
    }
