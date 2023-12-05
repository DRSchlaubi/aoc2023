package dev.schlaubi.aoc2023

import dev.schlaubi.aoc2023.util.mutableListIterator

private val mapSeperator = "(\\w+)-to-(\\w+) map:".toRegex()

private data class MapLine(val sourceStart: Long, val destinationStart: Long, val length: Long) {
    val sourceRange: LongRange
        get() = sourceStart until (sourceStart + length)
}

private data class Range(val start: Long, val length: Long) {
    val range: LongRange
        get() = start until (start + length)
}

object Day5 : AoCDay {
    override fun performTask1(input: String): Any {
        val (seeds, maps) = process(input)

        return seeds.mapWith(maps)
    }

    override fun performTask2(input: String): Any {
        val (seedsRaw, maps) = process(input)
        val seeds = seedsRaw.chunked(2).map { (start, length) -> Range(start, length) }.toMutableList()

        return maps.fold(seeds) { currentSeeds, currentMap ->
            val rangeIterator = currentSeeds.mutableListIterator()

            buildList {
                while (rangeIterator.hasNext()) {
                    val currentRange = rangeIterator.next()
                    val range = currentRange.range

                    val mapLine = currentMap.firstOrNull {
                        val sourceRange = it.sourceRange

                        sourceRange.first <= range.last && sourceRange.last >= range.first
                    }
                    if (mapLine == null) {
                        add(currentRange)
                        continue
                    }


                    val windowStart = (mapLine.sourceRange.first - range.first).coerceAtLeast(0)
                    val windowBacklog = (range.last - mapLine.sourceRange.last).coerceAtLeast(0)
                    val rangeDifference = mapLine.destinationStart - mapLine.sourceStart

                    val overlap = Range(range.first + windowStart, currentRange.length - windowBacklog)

                    add(overlap.copy(start = overlap.start + rangeDifference))
                    val windowEnd = overlap.range.last

                    if (mapLine.sourceRange.first > range.first) {
                        rangeIterator.add(Range(range.first, mapLine.sourceStart - range.first))
                    }
                    if (windowBacklog != 0L) {
                        rangeIterator.add(Range(windowEnd + 1, windowBacklog))
                    }

                }
            }.toMutableList()
        }.minOf(Range::start)
    }


    private fun Sequence<Long>.mapWith(maps: Iterable<List<MapLine>>) = minOf { it ->
        maps.fold(it) { acc, currentMap ->
            val mapLine = currentMap.firstOrNull { acc in it.sourceRange } ?: return@fold acc

            val rangeDifference = mapLine.destinationStart - mapLine.sourceStart

            acc + rangeDifference

        }
    }

    private fun process(input: String): Pair<Sequence<Long>, List<List<MapLine>>> {
        val lines = input.lines()
        val seeds = lines.first().substringAfter("seeds: ").split(" ").asSequence().map(String::toLong)

        val maps = input
                .drop(lines.first().length)
                .split(mapSeperator)
                .filterNot(String::isBlank)
                .map {
                    it.lines().filterNot(String::isBlank).map { line ->
                        val (destinationStart, sourceStart, length) = line.split(" ")
                        MapLine(sourceStart.toLong(), destinationStart.toLong(), length.toLong())
                    }
                }
        return seeds to maps
    }
}
