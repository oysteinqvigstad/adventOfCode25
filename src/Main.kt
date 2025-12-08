import java.io.File

fun main() {
    val input = File("inputs/input08.txt").readLines()

    Day08(input).also {
        println(it.answer1)
        println(it.answer2)
    }
}