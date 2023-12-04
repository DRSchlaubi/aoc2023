package dev.schlaubi.aoc2023

import kotlin.io.path.Path
import kotlin.io.path.readText
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.measureTimedValue

abstract class AoCTest {
    abstract val fileName: String
    abstract val task1ExampleInput: String
    abstract val task1ExampleResult: Any
    abstract val task1Result: Any
    open val task2ExampleInput: String
        get() = task1ExampleInput
    abstract val task2ExampleResult: Any
    abstract val task2Result: Any

    abstract val logic: AoCDay
    
    @Test
    fun `test example for task 1`() = runTest(task1ExampleResult) { logic.performTask1(task1ExampleInput) }

    @Test
    fun `test example for task 2`() = runTest(task2ExampleResult) { logic.performTask2(task2ExampleInput) }

    @Test
    fun `test task 1`() = runTest(task1Result) { logic.performTask1(readInput()) }

    @Test
    fun `test task 2`() = runTest(task2Result) { logic.performTask2(readInput()) }

    private inline fun <T> runTest(expexted: T, block: () -> T) {
        val value = measureTimedValue(block)

        println("Took: ${value.duration}")
        println("Result: ${value.value}")
        assertEquals(expexted, value.value)
    }

    private fun readInput() = Path("inputs/$fileName").readText()
}