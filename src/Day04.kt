class Day04(val input: List<String>) {

    val answer1 = clear(1)
    val answer2 = clear(Int.MAX_VALUE)

    /**
     * Clears paper rolls (from '@' to '.') reachable by forklifts, and repeats the
     * process until there is no more reachable paper in the printing department.
     */
    fun clear(maxLoops: Int): Int {
        val grid = Grid(input)
        for (i in 1..maxLoops) {
            grid.reachablePaper()?.forEach { c -> grid[c] = '.' } ?: break
        }
        return grid.mutationCount
    }

}

data class Coordinate(val x: Int, val y: Int)

class Grid(input: List<String>) {
    private val data = input.map { it.toMutableList() }
    private val coordinates = input.flatMapIndexed { y, row -> row.indices.map { x -> Coordinate(x, y) } }
    var mutationCount = 0

    fun reachablePaper(): List<Coordinate>? =
        coordinates.filter { c -> this[c] == '@' }
                   .filter { c -> neighbors2D(c).map { n -> this[n] == '@' }.count{ it } < 5 }
                   .takeIf { it.isNotEmpty() }

    fun neighbors2D(c: Coordinate): List<Coordinate> =
        (-1..1).flatMap { x -> (-1..1).map { y -> Coordinate(c.x+x, c.y+y) } }

    operator fun get(c: Coordinate): Char? =
        data.getOrNull(c.y)?.getOrNull(c.x)

    operator fun set(c: Coordinate, value: Char) {
        data[c.y][c.x] = value
        ++mutationCount
    }
}