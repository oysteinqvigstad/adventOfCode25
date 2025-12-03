import java.io.File

fun main() {
    val input = File("inputs/input03.txt").readLines()

    Day03(input).also {
        println(it.answer1)
        println(it.answer2)
    }
}