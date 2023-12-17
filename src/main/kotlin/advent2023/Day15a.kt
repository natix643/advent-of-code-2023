package advent2023

object Day15a {

    val example = "rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7"

    val steps = Input.day15().first().split(',')

    val result = steps.sumOf { step ->
        var hash = 0
        for (char in step) {
            hash = ((hash + char.code) * 17) % 256
        }
        hash
    }
}

fun main() {
    println(Day15a.result)
}
