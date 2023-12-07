package dev.schlaubi.aoc2023

object Day6 : AoCDay {
    override fun performTask1(input: String): Any {
        val (times, distances) = input.lines().map {
            it.substringAfter(":").split("\\s+".toRegex()).drop(1).map(String::toLong)
        }

        return processRaces(times, distances)
    }

    override fun performTask2(input: String): Any {
        val (time, distance) = input.lines().map {
            it.substringAfter(":").replace("\\s+".toRegex(), "").toLong()
        }

        return processRace(time, distance)
    }

    private fun processRaces(times: List<Long>, distances: List<Long>): Int {
        val races = times.zip(distances).map { (time, distance) ->
            processRace(time, distance)
        }

        return races.reduce { acc, i -> acc * i }
    }

    private fun processRace(time: Long, distance: Long): Int = (1..time).count {
        (time - it) * it > distance
    }

}
