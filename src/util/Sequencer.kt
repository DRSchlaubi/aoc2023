package dev.schlaubi.aoc2023.util

fun <T> Iterable<T>.cycle(): Sequence<T> {
    return sequence {
        while (true) yieldAll(this@cycle)
    }
}
