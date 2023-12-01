package advent2023

object Day01b {

    val example = """
        two1nine
        eightwothree
        abcone2threexyz
        xtwone3four
        4nineeightseven2
        zoneight234
        7pqrstsixteen
    """.trimIndent()

    val wordsToDigits = mapOf(
        "one" to 1,
        "two" to 2,
        "three" to 3,
        "four" to 4,
        "five" to 5,
        "six" to 6,
        "seven" to 7,
        "eight" to 8,
        "nine" to 9
    )

    fun extractDigits(line: String): String {
        val builder = StringBuilder()

        for (index in 0..<line.length) {
            var isWord = false
            for (word in wordsToDigits.keys) {
                if (line.startsWith(word, index)) {
                    builder.append(wordsToDigits[word])
                    isWord = true
                }
            }
            if (!isWord && line[index].isDigit()) {
                builder.append(line[index])
            }
        }

        return builder.toString()
    }

    val result = Input.day01()
        .map { extractDigits(it) }
        .sumOf { "${it.first()}${it.last()}".toInt() }
}

fun main() {
    println(Day01b.result)
}
