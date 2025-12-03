class Day03(input: List<String>) {
    val answer1 = input.sumOf { rating(it, 2) }
    val answer2 = input.sumOf { rating(it, 12) }

    /**
     * For a given battery bank, compute the highest 'joltage' rating,
     * by activating k batteries (from left to right)
     */
    fun rating(batteries: String, k: Int): Long {
        var start = 0
        var result = ""
        for (end in batteries.length-k+1 .. batteries.length) {
            start = (start until end).maxBy { batteries[it] } + 1
            result += batteries[start-1]
        }
        return result.toLong()
    }
}