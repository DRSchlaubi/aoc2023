package dev.schlaubi.aoc2023

import kotlin.io.path.Path
import kotlin.io.path.readText
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.measureTimedValue

abstract class AoCTest {
    abstract val fileName: String
    abstract val task1ExampleInput: String
    abstract val task1ExampleResult: Long
    abstract val task1Result: Long
    abstract val task2ExampleInput: String
    abstract val task2ExampleResult: Long
    abstract val task2Result: Long

    abstract val logic: AoCDay
    
    @Test
    fun `test example for task 1`() = runTimed { assertEquals(task1ExampleResult, logic.performTask1(task1ExampleInput)) }

    @Test
    fun `test example for task 2`() = runTimed { assertEquals(task2ExampleResult, logic.performTask2(task2ExampleInput)) }

    @Test
    fun `test task 1`() = runTimed { assertEquals(task1Result, logic.performTask1(readInput())) }

    @Test
    fun `test task 2`() = runTimed { assertEquals(task2Result, logic.performTask2(readInput())) }

    private inline fun <T> runTimed(block: () -> T): T {
        val value = measureTimedValue(block)

        println("Took: ${value.duration}")

        return value.value
    }

    private fun readInput() = Path("inputs/$fileName").readText()
}