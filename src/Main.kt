import java.io.File

fun main() {
    val input = File("inputs/input05.txt").readLines()

    Day05(input).also {
        println(it.answer1)
        println(it.answer2)
    }
}