package dev.schlaubi.aoc2023

class Day7Test : AoCTest() {
    override val fileName: String = "input7.txt"
    override val task1ExampleInput: String = """
    32T3K 765
    T55J5 684
    KK677 28
    KTJJT 220
    QQQJA 483
    """.trimIndent()
    override val task1ExampleResult: Any = 6440
    override val task1Result: Any = 255048101
    override val task2ExampleResult: Any = 5905
    override val task2Result: Any = 253718286
    override val logic: AoCDay = Day7
}
