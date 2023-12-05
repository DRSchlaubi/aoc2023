package dev.schlaubi.aoc2023

class Day5Test : AoCTest() {
    override val fileName: String = "input5.txt"
    override val task1ExampleInput: String = """
    seeds: 79 14 55 13

    seed-to-soil map:
    50 98 2
    52 50 48
    
    soil-to-fertilizer map:
    0 15 37
    37 52 2
    39 0 15
    
    fertilizer-to-water map:
    49 53 8
    0 11 42
    42 0 7
    57 7 4
    
    water-to-light map:
    88 18 7
    18 25 70
    
    light-to-temperature map:
    45 77 23
    81 45 19
    68 64 13
    
    temperature-to-humidity map:
    0 69 1
    1 0 69
    
    humidity-to-location map:
    60 56 37
    56 93 4
    """.trimIndent()
    override val task1ExampleResult: Any = 35L
    override val task1Result: Any = 579439039L
    override val task2ExampleResult: Any = 46L
    override val task2Result: Any get() = 7873084L
    override val logic: AoCDay = Day5

}