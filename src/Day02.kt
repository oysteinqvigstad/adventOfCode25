
class Day02(input: List<String>) {
    private val data = input[0].split(',').map { parse(it) }

    val answer1 = data.flatMap { range -> range.filter { number -> hasRepeatingPattern(number.toString(), 2) } }.sum()
    val answer2 = data.flatMap { range -> range.filter { number -> hasRepeatingPattern(number.toString()) } }.sum()

    fun parse(input: String): LongRange =
        input.split('-').let { (start, end) -> start.toLong()..end.toLong() }

    fun hasRepeatingPattern(s: String, k: Int? = null): Boolean =
        (k?.let { "^(.{${s.length/it}})\\1$" } ?: "^(.+?)\\1+$").toRegex().matches(s)
}