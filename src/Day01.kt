import java.lang.Math.floorDiv
import kotlin.math.absoluteValue

class Day01(input: List<String>) {
    val data = input.map { parse(it) }
    val accumulated = data.scan(50) { acc, value -> apply(acc, value) }
    val clicks = accumulated.zip(data) { acc, value -> clicks(acc, value) }

    val answer1 = accumulated.count { it == 0 }
    val answer2 = clicks.sum()


    fun parse(input: String): Int {
        val value = input.substring(1).toInt()
        return if (input[0] == 'R') value else -value
    }

    fun apply(value1: Int, value2: Int): Int {
        return ((value1 + value2) % 100 + 100) % 100
    }

    fun clicks(value1: Int, value2: Int): Int {
        val start = floorDiv(value1, 100)
        val end = floorDiv(value1 + value2, 100)
        var diff = (end - start).absoluteValue
        if (value2 < 0) {
            if (apply(value1, value2) == 0) diff++
            if (value1 == 0) diff--
        }
        return diff
    }
}