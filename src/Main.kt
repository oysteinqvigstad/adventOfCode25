import java.io.File

fun main() {
    val input = File("inputs/input07.txt").readLines()

    Day07(input).also {
        println(it.answer1)
        println(it.answer2)
    }
}