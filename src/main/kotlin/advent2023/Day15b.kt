package advent2023

import java.util.*

object Day15b : Day15() {

    class Hashmap {
        val boxes = Array(hashmapSize) { LinkedList<Lens>() }

        fun remove(label: String) {
            boxes[hash(label)].removeIf { it.label == label }
        }

        fun put(label: String, focalLength: Int) {
            val box = boxes[hash(label)]
            val lens = box.find { it.label == label }
            if (lens != null) {
                lens.focalLength = focalLength
            } else {
                box += Lens(label, focalLength)
            }
        }
    }

    data class Lens(
        val label: String,
        var focalLength: Int
    )

    fun parseHashmap(steps: List<String>): Hashmap {
        val hashmap = Hashmap()
        steps.forEach { step ->
            when {
                step.contains("=") -> {
                    val (label, focalLength) = step.split("=")
                    hashmap.put(label, focalLength.toInt())
                }
                step.contains("-") -> {
                    val label = step.dropLast(1)
                    hashmap.remove(label)
                }
            }
        }
        return hashmap
    }

    fun focusingPower(hashmap: Hashmap): Int {
        var result = 0
        hashmap.boxes.forEachIndexed { boxIndex, box ->
            box.forEachIndexed { lensIndex, lens ->
                result += (boxIndex + 1) * (lensIndex + 1) * lens.focalLength
            }
        }
        return result
    }

    val steps = Input.day15().first().split(',')
    val hashmap = parseHashmap(steps)
    val result = focusingPower(hashmap)
}

fun main() {
    println(Day15b.result)
}
