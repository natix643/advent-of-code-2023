package advent2023

object Day09b : Day09() {

    fun extrapolateBackward(triangle: List<MutableList<Int>>) {
        for (i in triangle.size - 2 downTo 0) {
            triangle[i].add(0, triangle[i][0] - triangle[i + 1][0])
        }
    }

    val result = Input.day09().sumOf {
        val ints = parseLine(it)
        val triangle = generateTriangle(ints)
        extrapolateBackward(triangle)
        triangle[0][0]
    }
}

fun main() {
    println(Day09b.result)
}
