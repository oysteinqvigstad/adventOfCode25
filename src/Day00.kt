import kotlin.math.abs

class Day00(input: List<String>) {
    private val left = input.map { it.substringBefore(' ').toInt() }.sorted()
    private val right = input.map { it.substringAfterLast(' ').toInt() }.sorted()

    val answer1 = left.zip(right).sumOf { abs(it.first - it.second) }
    val answer2 = left.sumOf { l -> l * right.count { it == l } }
}
