package dev.schlaubi.aoc2023

object Day1 : AoCDay {
    override fun performTask1(input: String): Long = input.lines().processLines().toLong()

    override fun performTask2(input: String): Long = input.lines().processLines(String::transformWordNumbersToNumeric).toLong()
}


inline fun Iterable<String>.processLines(preProcess: String.() -> String = { this }) = sumOf {
    val chars = preProcess(it).toCharArray()
        .filter(Char::isDigit)

    (chars.first().toString() + chars.last()).toInt()
}

fun String.transformWordNumbersToNumeric(): String {
    val wordToNumberMapping = mapOf(
        "one" to "1",
        "two" to "2",
        "three" to "3",
        "four" to "4",
        "five" to "5",
        "six" to "6",
        "seven" to "7",
        "eight" to "8",
        "nine" to "9",
        "zero" to "0"
    )

    return buildString {
        val iterator = this@transformWordNumbersToNumeric.iterator()
        var currentWord: String? = iterator.next().toString()
        while (currentWord != null) {
            if (currentWord.lastOrNull()?.isDigit() == true) {
                append(currentWord.last())
                currentWord = iterator.nextOrNull()?.toString()
            } else {
                if (wordToNumberMapping.none { (word) -> word.startsWith(currentWord!!) }) {
                    currentWord = currentWord.drop(1)
                } else {
                    currentWord += iterator.nextOrNull() ?: break
                }
                val number = wordToNumberMapping[currentWord]
                if (number != null) {
                    append(number)
                }
            }
        }
    }
}

private fun <T> Iterator<T>.nextOrNull() = if (hasNext()) next() else null