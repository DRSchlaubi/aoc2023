package dev.schlaubi.aoc2023

private data class Number(val y: Int, val startX: Int, val endX: Int, val value: Int) {
    val width get() = endX - startX + 1
}

private data class Symbol(val character: Char, val coordinates: Pair<Int, Int>) {
    override fun hashCode(): Int = coordinates.hashCode()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Symbol

        return coordinates == other.coordinates
    }
}
private data class ValidNumber(val symbol: Symbol, val number: Number)

private val digitRegex = "\\d+".toRegex()

object Day3 : AoCDay {
    override fun performTask1(input: String): Int = process(input).sumOf { it.number.value }

    override fun performTask2(input: String): Int = process(input)
            .asSequence()
            .filter { it.symbol.character == '*' }
            .groupBy(ValidNumber::symbol)
            .filter { (_, values) -> values.size == 2 }
            .map { (_, values) -> values }
            .sumOf { (first, last) -> first.number.value * last.number.value }


    private fun process(input: String): List<ValidNumber> {
        val grid = input.lines().reversed()

        val numbers = grid.flatMapIndexed { y: Int, row ->
            digitRegex.findAll(row).map {
                val value = it.value.toInt()
                Number(y, it.range.first, it.range.last, value)
            }
        }

        return numbers.mapNotNull {
            val symbol = it.adjecentPoints.mapNotNull map@{ (x, y) ->
                val point = grid.getOrNull(y)?.getOrNull(x) ?: return@map null
                val symbol = point.takeIf { point != '.' && !point.isDigit() }?: return@map null
                Symbol(symbol, x to y)
            }.firstOrNull() ?: return@mapNotNull null

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
