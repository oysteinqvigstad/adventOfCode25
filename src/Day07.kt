data class Pos(val x: Int, val y: Int)
data class Node(val pos: Pos, val children: List<Pos>, var visited: Boolean = false)


class Day07(val input: List<String>) {
    // memoization of unique number of paths that can be taken from a split
    val memo = mutableMapOf<Node, Long>()

    // splitter node lookup
    val nodes: Map<Pos, Node> = input.withIndex()
                                     .flatMap { (y, row) -> row.indices.map { x -> Pos(x, y) } }
                                     .filter(::isSplit)
                                     .associate { Pair(it, Node(it, findNextSplits(it))) }

    val answer1 = countSplits()
    val answer2 = countUniquePaths()

    /**
     * Using DFS, count every splitter encountered from start
     */
    fun countSplits(n: Node = nodes.values.first()): Int = when {
        n.visited -> 0
        else -> 1 + n.children.mapNotNull(nodes::get).sumOf(::countSplits).also { n.visited = true }
    }

    /**
     * Using DFS, count every unique path that can be taken from start
     */
    fun countUniquePaths(n: Node = nodes.values.first()): Long =
        2 - n.children.size + memo.getOrPut(n) {
            n.children.mapNotNull(nodes::get).sumOf(::countUniquePaths)
        }

    /**
     * Scan downward in the 2D string array for the next splitter (child)
     */
    fun findNextSplits(p: Pos): List<Pos> =
        listOf(Pos(p.x-1, p.y), Pos(p.x+1, p.y))
            .mapNotNull{ p -> (p.y until input.size)
                .map{ Pos(p.x, it) }.firstOrNull(::isSplit)
            }

    fun isSplit(p: Pos): Boolean =
        input[p.y][p.x] == '^'
}

