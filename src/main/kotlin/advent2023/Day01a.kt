package advent2023

object Day01a {

    val example = """
        1abc2
        pqr3stu8vwx
        a1b2c3d4e5f
        treb7uchet
    """.trimIndent()

    val result = Input.day01().sumOf { line ->
        val digits = line.filter { it.isDigit() }
        "${digits.first()}${digits.last()}".toInt()
    }
}

fun main() {
    println(Day01a.result)
}
