package dev.schlaubi.aoc2023

class Day1Test : AoCTest() {
    override val fileName: String = "input1.txt"
    override val task1ExampleInput: String = """
    1abc2
    pqr3stu8vwx
    a1b2c3d4e5f
    treb7uchet
    """.trimIndent()
    override val task1ExampleResult: Int = 142
    override val task1Result: Int = 55834
    override val task2ExampleInput: String = """
    two1nine
    eightwothree
    abcone2threexyz
    xtwone3four
    4nineeightseven2
    zoneight234
    7pqrstsixteen
    """.trimIndent()
    override val task2ExampleResult: Int = 281
    override val task2Result: Int = 53221
    override val logic: AoCDay = Day1

}