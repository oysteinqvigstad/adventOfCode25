fun String.toRange(): LongRange = split('-').let { (a, b) -> a.toLong()..b.toLong() }

class Day05(input: List<String>) {
    val freshRanges = input.takeWhile(String::isNotEmpty).map(String::toRange).run(::mergeOverlap)
    val inventory = input.mapNotNull(String::toLongOrNull)

    val answer1 = inventory.count { id -> freshRanges.any { range -> id in range } }
    val answer2 = freshRanges.sumOf { range -> range.last - range.first + 1L }

    fun mergeOverlap(ranges: List<LongRange>): List<LongRange> {
        val sorted = ranges.sortedBy(LongRange::first)
        val merged = mutableListOf(sorted.first())
        for (curr in sorted.drop(1)) {
            val prev = merged.last()
            if (curr.first > prev.last) {
                merged.add(curr)  // no overlap with previous.
            } else {
                merged[merged.size-1] = prev.first..maxOf(curr.last, prev.last)
            }
        }
        return merged
    }
}