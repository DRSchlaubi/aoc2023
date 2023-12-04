package dev.schlaubi.aoc2023

import kotlin.math.max


data class Game(val id: Int, val rounds: List<Round>) {

    data class Round(val red: Int, val green: Int, val blue: Int) {
        val isPossible: Boolean
            get() {
                if (red > 12) return false
                if (green > 13) return false
                if (blue > 14) return false

                return true
            }

    }

    data class Cube(val count: Int, val color: Color) {
        enum class Color(val gameName: String) {
            RED("red"),
            GREEN("green"),
            BLUE("blue");

            companion object {
                fun fromName(name: String) = entries.first { it.gameName == name }
            }
        }
    }

    val isPossible: Boolean
        get() = rounds.all(Round::isPossible)
}

val gameIdRegex = """Game (\d+)""".toRegex()
val cubeRegex = """(\d+) (red|green|blue)""".toRegex()

object Day2 : AoCDay {

    override fun performTask1(input: String): Int = parse(input)
            .filter(Game::isPossible)
            .sumOf(Game::id)

    override fun performTask2(input: String): Int = parse(input)
            .map { game ->
                game.rounds.reduce { round1, round2 ->
                    Game.Round(
                            max(round1.red, round2.red),
                            max(round1.green, round2.green),
                            max(round1.blue, round2.blue),
                    )
                }
            }
            .map { it.red * it.green * it.blue }
            .sum()

    private fun parse(input: String) = input.lines()
            .asSequence()
            .map {
                val (gameId) = gameIdRegex.find(it)!!.destructured
                val rounds = it.substringAfter(':').split(';').map { line ->
                    val cubes = cubeRegex.findAll(line).map { match ->
                        val (count, color) = match.destructured

                        Game.Cube(count.toInt(), Game.Cube.Color.fromName(color))
                    }.toList()

                    fun List<Game.Cube>.count(color: Game.Cube.Color) = filter { cube -> cube.color == color }
                            .sumOf(Game.Cube::count)

                    Game.Round(
                            cubes.count(Game.Cube.Color.RED),
                            cubes.count(Game.Cube.Color.GREEN),
                            cubes.count(Game.Cube.Color.BLUE),
                    )
                }
                Game(gameId.toInt(), rounds)
            }


}