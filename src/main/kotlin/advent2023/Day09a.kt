package advent2023

object Day09a : Day09() {

    fun extrapolateForward(triangle: List<MutableList<Int>>) {
        for (i in triangle.size - 2 downTo 0) {
            triangle[i].add(triangle[i].last() + triangle[i + 1].last())
        }
    }

    val result = Input.day09().sumOf {
        val ints = parseLine(it)
        val triangle = generateTriangle(ints)
        extrapolateForward(triangle)
        triangle.first().last()
    }
}

fun main() {
    println(Day09a.result)
}
