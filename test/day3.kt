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
    override val task1ExampleResult: Int = 4361
    override val task2ExampleResult: Int = 467835
    override val task1Result: Int = 550934
    override val task2Result: Int = 81997870
    override val logic: AoCDay = Day3

}
