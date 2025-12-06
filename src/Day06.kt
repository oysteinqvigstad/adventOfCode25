typealias Op = (Long, Long) -> Long

class Day06(input: List<String>) {
    val table = input.map(::splitByWhitespace)
    val operators = table.last().map(::makeOperator)
    val values = makeValues(input.dropLast(1), getColumnWidths(table))

    val answer1 = values.zip(operators).sumOf { (value, op) -> computeHorizontally(value, op) }
    val answer2 = values.zip(operators).sumOf { (value, op) -> computeVertically(value, op) }

    /**
     * Extracts numbers (as strings) while preserving vertical alignment
     */
    fun makeValues(input: List<String>, widths: List<Int>): List<List<String>> =
        widths.drop(1)
              .runningFold(0..<widths.first()) { prev, value -> prev.last+2..prev.last+1+value }
              .map { r -> input.map { it.substring(r) } }

    /**
     * Calculate group by applying operator to each number
     */
    fun computeHorizontally(values: List<String>, op: Op) : Long =
        values.map { s -> s.trim().toLong() }.reduce { a, b -> op(a, b) }

    /**
     * Compute group by transposing digit values first
     */
    fun computeVertically(values: List<String>, op: Op) : Long =
        values.first().indices
            .map { col -> values.map { it[col] }.joinToString("") }
            .run { computeHorizontally(this, op) }

    /**
     * Values have variable length, this computes the size of the entire column
     * by selecting the size of the value with the largest width
     */
    fun getColumnWidths(table: List<List<String>>): List<Int> =
        table.first().indices
            .map { col -> (0 until table.size).maxOf { row -> table[row][col].length } }


    fun makeOperator(token: String): Op = when (token) {
        "+" -> { a, b -> a + b }
        "*" -> { a, b -> a * b }
        else -> throw(IllegalArgumentException("illegal operation"))
    }

    fun splitByWhitespace(input: String): List<String> =
        input.trim().split(Regex("\\s+"))
}