fun String.toRange(): LongRange = split('-').let { (a, b) -> a.toLong()..b.toLong() }

class Day05(input: List<String>) {
    val ranges = mergeOverlapping(input.takeWhile(String::isNotEmpty).map(String::toRange))
    val digits = input.mapNotNull(String::toLongOrNull)

    val answer1 = digits.count { digit -> ranges.any { range -> digit in range } }
    val answer2 = ranges.sumOf { range -> range.last - range.first + 1L }

    fun mergeOverlapping(ranges: List<LongRange>): List<LongRange> {
        val sorted = ranges.sortedBy(LongRange::first)
        val result = mutableListOf<LongRange>()
        result.add(sorted.first())
        for (current in sorted.drop(1)) {
            val last = result.last()
            if (current.first > last.last) {
                result.add(current)  // no overlap with previous.
            } else {
                result[result.size-1] = last.first..maxOf(current.last, last.last)
            }
        }
        return result
    }
}


