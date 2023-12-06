package dev.schlaubi.aoc2023

class Day6Test : AoCTest() {
    override val fileName: String = "input6.txt"
    override val task1ExampleInput: String = """
    Time:      7  15   30
    Distance:  9  40  200
    """.trimIndent()
    override val task1ExampleResult: Any = 288
    override val task1Result: Any = 345015
    override val task2ExampleResult: Any = 71503
    override val task2Result: Any = 42588603
    override val logic: AoCDay = Day6

}