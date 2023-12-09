package dev.schlaubi.aoc2023

object Day9 : AoCDay {
    override fun performTask1(input: String): Any = process(input).extrapolate { acc, row -> row.last() + acc }

    override fun performTask2(input: String): Any = process(input).extrapolate { acc, row -> row.first() - acc }

    private fun Sequence<List<List<Int>>>.extrapolate(extrapolator: (acc: Int, row: List<Int>) -> Int) = map {
        it.reversed().fold(0, extrapolator)
    }.sum()

    private fun process(input: String) = input.lines()
            .asSequence()
            .map {
                val numbers = it.split(" ").map(String::toInt)

                numbers.generateDiffrences()
            }
}

private fun List<Int>.generateDiffrences(backlog: List<List<Int>> = listOf(this)): List<List<Int>> {
    val round = zipWithNext().map { (previous, it) ->
        it - previous
    }

    return if (round.any { it != 0 }) {
        round.generateDiffrences(backlog + listOf(round))
    } else {
        backlog + listOf(round)
    }
}
