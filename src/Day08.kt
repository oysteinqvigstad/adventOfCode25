import kotlin.math.pow
import kotlin.math.sqrt

class Day08(val input: List<String>) {
    val boxes = input.map { it.split(',').map(String::toDouble).let { (x, y, z) -> Box(x, y, z) } }

    val cables = boxes.flatMapIndexed { index, from -> boxes.drop(index+1).map { to -> Cable(from, to) } }
                      .sortedBy { it.distance }

    val answer1 = UnionFind(boxes).also { uf -> cables.take(1000).forEach { uf.union(it.from, it.to) } }
                                  .components()
                                  .values
                                  .sortedDescending()
                                  .take(3)
                                  .reduce(Int::times)

    val answer2 = UnionFind(boxes).let { uf -> cables.indexOfFirst{ uf.union(it.from, it.to) } }
                                  .let { index -> cables[index] }
                                  .let { cable -> cable.from.x.toLong() * cable.to.x.toLong()  }


}


/**
 * Graph node equivalent
 */
data class Box(val x: Double, val y: Double, val z: Double) {
    fun norm(other: Box): Double =
        sqrt((x-other.x).pow(2) + (y-other.y).pow(2) + (z-other.z).pow(2))
}

/**
 * Graph edge equivalent
 */
data class Cable(val from: Box, val to: Box) {
    val distance = from.norm(to)
}

/**
 * Naive implementation of union find (but fast enough for this problem)
 */
class UnionFind(nodes: List<Box>) {
    private val comp = nodes.associateWith { it }.toMutableMap()
    private var k = comp.size

    fun find(u: Box): Box = when {
        comp[u] == u -> u
        else -> find(comp[u]!!)
    }

    fun union(u: Box, v: Box): Boolean =
        (find(u) to find(v)).let { (r1, r2) -> when {
                r1 == r2 -> k == 1
                else -> (--k == 1).also { comp[r1] = r2 }
            }
        }

    fun components() =
        comp.keys.groupingBy { find(it) }.eachCount()
}
