fun String.toRange(): LongRange = split('-').let { (a, b) -> a.toLong()..b.toLong() }

class Day05(input: List<String>) {
    val freshRanges = mergeOverlapping(input.takeWhile(String::isNotEmpty).map(String::toRange))
    val inventory = input.mapNotNull(String::toLongOrNull)

    val answer1 = inventory.count { id -> freshRanges.any { range -> id in range } }
    val answer2 = freshRanges.sumOf { range -> range.last - range.first + 1L }

    fun mergeOverlapping(ranges: List<LongRange>): List<LongRange> {
        val sorted = ranges.sortedBy(LongRange::first)
        val merged = mutableListOf<LongRange>()
        merged.add(sorted.first())
        for (current in sorted.drop(1)) {
            val last = merged.last()
            if (current.first > last.last) {
                merged.add(current)  // no overlap with previous.
            } else {
                merged[merged.size-1] = last.first..maxOf(current.last, last.last)
            }
        }
        return merged
    }
}


