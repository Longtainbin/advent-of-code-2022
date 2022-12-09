import kotlin.math.abs

/**
 * part2部分的没有求解处理
 *
 * 我没明白节点到底怎么移动的
 */

val moveOpeMap = initMoveOpe()

fun main() {
    val input = readInput("input")

    fun part1(input: List<String>): Int {
        return helperForPart1(input)
    }


    fun part2(input: List<String>): Int {
        return helperForPart2(input)
    }

    println(part1(input))
    println(part2(input))


}

/* For Part1 */
private fun helperForPart1(input: List<String>): Int {
    val head = intArrayOf(0, 0)
    val tail = intArrayOf(0, 0)
    val count = mutableSetOf<Pair<Int, Int>>()
    count.add(Pair(tail[0], tail[1]))

    for (s in input) {
        val strArr = s.split(" ")
        val symbol = strArr[0]
        val n = strArr[1].toInt()
        for (i in 1..n) {
            move(head, tail, moveOpeMap[symbol]!!, count)
        }
    }
    return count.size
}


/* For Part2 */
private fun helperForPart2(input: List<String>): Int {
    val rope = Array(10) { intArrayOf(0, 0) }
    val count = mutableSetOf<Pair<Int, Int>>()
    count.add(Pair(0, 0))

    for (s in input) {
        val strArr = s.split(" ")
        val symbol = strArr[0]
        val n = strArr[1].toInt()
        for (i in 1..n) {
            println("$symbol $i")
            moveForPart2(rope, moveOpeMap[symbol]!!, count)
        }
    }
    return count.size
}

private fun moveForPart2(
    rope: Array<IntArray>,
    moveOpe: (IntArray) -> Unit,
    count: MutableSet<Pair<Int, Int>>
) {
    val tempRope = Array(10) { intArrayOf(0, 0) }
    for (i in tempRope.indices) {
        tempRope[i].setValues(rope[i])
    }
    moveOpe(rope[0])
    var index = 1
    while (index <= 9 && !isTouching(rope[index - 1], rope[index])) {
        rope[index].setValues(tempRope[index - 1])
        index++
    }
    count.add(Pair(rope[9][0], rope[9][1]))

    println(rope.joinToString { "(${it[0]},${it[1]})" })
}


/* common function */
private fun initMoveOpe(): Map<String, (IntArray) -> Unit> {
    val map = mutableMapOf<String, (IntArray) -> Unit>()
    map["R"] = { head -> head[0] = head[0] + 1 }
    map["L"] = { head -> head[0] = head[0] - 1 }
    map["U"] = { head -> head[1] = head[1] + 1 }
    map["D"] = { head -> head[1] = head[1] - 1 }
    return map
}


private fun move(
    head: IntArray,
    tail: IntArray,
    moveOpe: (IntArray) -> Unit,
    count: MutableSet<Pair<Int, Int>>
) {
    val temp = head.clone()
    moveOpe(head)
    if (!isTouching(head, tail)) {
        tail[0] = temp[0]
        tail[1] = temp[1]
        count.add(Pair(tail[0], tail[1]))
    }
//    println("${tail[0]}  ${tail[1]}")
}


private fun isTouching(head: IntArray, tail: IntArray): Boolean {
    return (abs(head[0] - tail[0]) <= 1 && abs(head[1] - tail[1]) <= 1)
}

// PS: 新冠真的难受

