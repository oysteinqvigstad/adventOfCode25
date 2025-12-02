
class Day02(input: List<String>) {
    private val data = input[0].split(',').map { parse(it) }

    val answer1 = data.flatMap { range -> range.filter { number -> hasRepeatingPattern(number, false) } }.sum()
    val answer2 = data.flatMap { range -> range.filter { number -> hasRepeatingPattern(number, true) } }.sum()

    fun parse(input: String): LongRange =
        input.split('-').let { (start, end) -> start.toLong()..end.toLong() }

    fun hasRepeatingPattern(n: Long, multipleRepeat: Boolean): Boolean =
        Regex("^(.+)\\1${if (multipleRepeat) "+" else ""}$").matches(n.toString())
}