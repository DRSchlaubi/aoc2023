package dev.schlaubi.aoc2023

class Day2Test : AoCTest() {
    override val fileName: String = "input2.txt"
    override val task1ExampleInput: String = """
    Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
    Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
    Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
    Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
    Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
    """.trimIndent()
    override val task1ExampleResult: Int = 8
    override val task1Result: Int = 2771
    override val task2ExampleResult: Int = 2286
    override val task2Result: Int = 70924
    override val logic: AoCDay = Day2

}