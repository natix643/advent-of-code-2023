package advent2023

object Day03a : Day03() {

    val lines = Input.day03()
    val regex = Regex("""\d+""")

    fun charsFromLine(lineIndex: Int, charIndexes: IntRange): List<Char> {
        val line = lines.getOrNull(lineIndex)
        return if (line != null) {
            val middleChars = line.substring(charIndexes).toList()
            val leftChar = line.getOrNull(charIndexes.first - 1)
            val rightChar = line.getOrNull(charIndexes.last + 1)
            (listOf(leftChar) + middleChars + rightChar).filterNotNull()
        } else {
            emptyList()
        }
    }

    val partNumbers = lines.flatMapIndexed { index, line ->
        regex.findAll(line).filter { match ->
            val leftChar = line.getOrNull(match.range.first - 1)
            val rightChar = line.getOrNull(match.range.last + 1)
            val topChars = charsFromLine(index - 1, match.range)
            val bottomChars = charsFromLine(index + 1, match.range)

            val adjacentChars = listOf(leftChar, rightChar) + topChars + bottomChars
            adjacentChars.any { it != null && it != '.' }
        }.map { match ->
            match.value.toInt()
        }
    }

    val result = partNumbers.sum()
}

fun main() {
    println(Day03a.result)
}
