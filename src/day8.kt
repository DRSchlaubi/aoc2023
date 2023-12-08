package dev.schlaubi.aoc2023

import dev.schlaubi.aoc2023.util.cycle
import dev.schlaubi.aoc2023.util.lcm
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking

private val mapLine = "(\\w{3}) = \\((\\w{3}), (\\w{3})\\)".toRegex()

private data class Map(val from: String, val left: String, val right: String)

object Day8 : AoCDay {
    override fun performTask1(input: String): Any {
        val (instructions, map) = process(input)
        
        
        var currentMap = map["AAA"]!!
        var steps = 0
        while (currentMap.from != "ZZZ") {
            val step = instructions.drop(steps).first()
            steps++
            println("Step: $steps (to: $step)")
            val nextStep = if(step == 'L') {
                currentMap.left
            } else {
                currentMap.right
            }
            println("next hop: $nextStep")
            if (nextStep == "ZZZ") break
            currentMap = map[nextStep]!!
            println("Done")
        }
        
        return steps
    }

    override fun performTask2(input: String): Any = runBlocking {
        val (instructions, map) = process(input)
        val startingNodes = map.filter { (from) -> from.endsWith("A") }
        
        startingNodes.map {
            async {
                var (_, currentMap) = it
                var steps = 0
                while (!currentMap.from.endsWith("Z")) {
                    val step = instructions.drop(steps).first()
                    steps++
                    println("Step: $steps (to: $step)")
                    val nextStep = if(step == 'L') {
                        currentMap.left
                    } else {
                        currentMap.right
                    }
                    println("next hop: $nextStep")
                    if (nextStep.endsWith("Z")) break
                    currentMap = map[nextStep]!!
                    println("Done")
                }

                steps
            }
        }.awaitAll().map(Number::toLong).lcm()
    }
    
    private fun process(input: String): Pair<Sequence<Char>, kotlin.collections.Map<String, Map>> {
        val lines = input.lines()
        val instructionLine = lines.first()
        val instructions = instructionLine.asIterable().cycle()

        val map = lines
            .asSequence()
            .drop(1)
            .filterNot(String::isBlank)
            .map {
                println("Line: " + it)
                val (from, left, right) = mapLine.find(it)!!.destructured

                Map(from, left, right)
            }.associateBy(Map::from)
        
        return instructions to map
    }

}