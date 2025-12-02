import java.lang.Math.floorDiv
import kotlin.math.abs

class Day01(input: List<String>) {
    private val data = input.map { parse(it) }
    private val prefixSums = data.scan(50) { prev, distance -> rotate(prev, distance) }

    val answer1 = prefixSums.count { it == 0 }
    val answer2 = prefixSums.zip(data) { prev, distance -> countClicks(prev, distance) }.sum()


    fun parse(input: String): Int =
        input.drop(1).toInt() * if (input[0] == 'R') 1 else -1

    /**
     * Rotate the dial, numbered 0-99
     */
    fun rotate(prev: Int, distance: Int): Int =
        (prev + distance).mod(100)

    /**
     * Count how many clicks (dial at pos 0), either by ending on 0, or rotating beyond it.
     * @note edge case (a): going counter-clockwise and ending on 0
     * @note edge case (b): going counter-clockwise and starting on 0
     */
    fun countClicks(prev: Int, distance: Int): Int {
        var diff = abs(floorDiv(prev + distance, 100) - floorDiv(prev, 100))
        if (distance < 0 && rotate(prev, distance) == 0) diff++  // (a)
        if (distance < 0 && prev == 0) diff--                    // (b)
        return diff
    }
}