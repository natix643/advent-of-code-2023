package advent2023

open class Day15 {

    val example = "rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7"

    val hashmapSize = 256

    fun hash(step: String): Int {
        var result = 0
        for (char in step) {
            result = ((result + char.code) * 17) % hashmapSize
        }
        return result
    }
}
