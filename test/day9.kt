package dev.schlaubi.aoc2023

class Day9Test : AoCTest() {
    override val fileName: String = "input9.txt"
    override val task1ExampleInput: String = """
    0 3 6 9 12 15
    1 3 6 10 15 21
    10 13 16 21 30 45
    """.trimIndent()
    override val task1ExampleResult: Any = 114
    override val task1Result: Any = 1647269739
    override val task2ExampleResult: Any = 2
    override val task2Result: Any = 864
    override val logic: AoCDay = Day9

}