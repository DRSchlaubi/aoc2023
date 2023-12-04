package dev.schlaubi.aoc2023

class Day3Test : AoCTest() {
    override val fileName: String = "input3.txt"
    override val task1ExampleInput: String = """
    467..114..
    ...*......
    ..35..633.
    ......#...
    617*......
    .....+.58.
    ..592.....
    ......755.
    ...$.*....
    .664.598..
    """.trimIndent()
    override val task1ExampleResult: Long = 4361
    override val task2ExampleResult: Long = 467835
    override val task1Result: Long = 550934
    override val task2Result: Long = 81997870
    override val logic: AoCDay = Day3

}
