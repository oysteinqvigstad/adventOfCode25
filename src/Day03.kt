class Day03(input: List<String>) {
    val answer1 = input.sumOf { rating(it, 2) }
    val answer2 = input.sumOf { rating(it, 12) }

    /**
     * For a given battery bank, compute the highest 'joltage' rating,
     * by activating k batteries (from left to right)
     */
    fun rating(batteries: String, k: Int): Long {
        var joltage: Long = 0
        var start = 0
        for (end in batteries.length-k+1 .. batteries.length) {
            start = (start until end).maxBy { batteries[it] } + 1
            joltage = joltage * 10 + batteries[start-1].digitToInt()
        }
        return joltage
    }
}