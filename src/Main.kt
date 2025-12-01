import java.io.File

fun main() {
    val input = File("inputs/input01.txt").readLines()

    Day01(input).also {
        println(it.answer1)
        println(it.answer2)
    }
}