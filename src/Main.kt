import java.io.File

fun main() {
    val input = File("inputs/input06.txt").readLines()

    Day06(input).also {
        println(it.answer1)
        println(it.answer2)
    }
}