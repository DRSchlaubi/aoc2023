package dev.schlaubi.aoc2023
class Day8Test : AoCTest() {
    override val fileName: String = "input8.txt"
    override val task1ExampleInput: String = """
    LLR

    AAA = (BBB, BBB)
    BBB = (AAA, ZZZ)
    ZZZ = (ZZZ, ZZZ)  
    """.trimIndent()
        
    override val task1ExampleResult: Any = 6
    override val task1Result: Any = 22199
    override val task2ExampleInput: String = """
    LR

    11A = (11B, XXX)
    11B = (XXX, 11Z)
    11Z = (11B, XXX)
    22A = (22B, XXX)
    22B = (22C, 22C)
    22C = (22Z, 22Z)
    22Z = (22B, 22B)
    XXX = (XXX, XXX)
    """.trimIndent()
    override val task2ExampleResult: Any = 6L
    override val task2Result: Any = 13334102464297
    override val logic: AoCDay = Day8

}