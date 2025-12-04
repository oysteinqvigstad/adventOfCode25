import java.io.File

fun main() {
    val input = File("inputs/input04.txt").readLines()

    Day04(input).also {
        println(it.answer1)
        println(it.answer2)
    }
}