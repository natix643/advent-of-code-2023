package advent2023

open class Day05 {

    val example = """
        seeds: 79 14 55 13

        seed-to-soil map:
        50 98 2
        52 50 48

        soil-to-fertilizer map:
        0 15 37
        37 52 2
        39 0 15

        fertilizer-to-water map:
        49 53 8
        0 11 42
        42 0 7
        57 7 4

        water-to-light map:
        88 18 7
        18 25 70

        light-to-temperature map:
        45 77 23
        81 45 19
        68 64 13

        temperature-to-humidity map:
        0 69 1
        1 0 69

        humidity-to-location map:
        60 56 37
        56 93 4
    """.trimIndent()

    data class Range(
        val sourceRange: LongRange,
        val destinationDelta: Long
    ) {
        operator fun get(source: Long): Long? =
            if (source in sourceRange) source + destinationDelta else null

        companion object {
            fun parse(line: String): Range {
                val (destinationStart, sourceStart, length) = line.split(" ").map { it.toLong() }
                return Range(
                    sourceRange = sourceStart..<sourceStart + length,
                    destinationDelta = destinationStart - sourceStart
                )
            }
        }
    }

    data class Mapping(
        val ranges: List<Range>
    ) {
        operator fun get(source: Long): Long =
            ranges.firstNotNullOfOrNull { it[source] } ?: source

        companion object {
            fun parse(lines: List<String>) = Mapping(
                ranges = lines.map { Range.parse(it) }
            )
        }
    }

    fun parseSeeds(input: String): List<Long> {
        return Regex("""\d+""").findAll(input.lineSequence().first())
            .map { it.value.toLong() }
            .toList()
    }

    fun parseMappings(input: String): List<Mapping> {
        return input.split(Regex("""\n\n"""))
            .drop(1)
            .map { Mapping.parse(it.lines().drop(1)) }
    }
}
