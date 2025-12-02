import java.io.File

fun main() {
    val input = File("inputs/input02.txt").readLines()

    Day02(input).also {
        println(it.answer1)
        println(it.answer2)
    }
}