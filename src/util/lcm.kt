package dev.schlaubi.aoc2023.util

import kotlin.math.abs

fun Collection<Long>.lcm(): Long {
    var lcm = 1L

    this.forEach { num -> 
        if (lcm == 1L)
            lcm = num
        else {
            val gcd = gcd(lcm, num)
            lcm = abs(lcm * num) / gcd
        }
    }

    return lcm
}

fun gcd(num1: Long, num2: Long): Long {
    if(num2 == 0L) {
        return num1
    }
    return gcd(num2, num1 % num2)
}