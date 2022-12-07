package day07

import Reference
import readInput
import kotlin.math.min

fun main() {
    val input = readInput("inputDay07")
    val root = generateTree(input)
    calculateDirectorySize(root)

    fun part1(root: Node): Long {
        return helperForPart1(root)
    }


    fun part2(root: Node): Long {
        return helperForPart2(root)
    }

    println(part1(root))
    println(part2(root))


}

/**
 * For part_1
 */
fun helperForPart1(root: Node): Long {
    val limit = 100000L
    val result = Reference<Long>(0L)
    dfsForPart1(root, result, limit)
    return result.value
}

fun dfsForPart1(root: Node, result: Reference<Long>, limit: Long) {
//    println("${root.name} \t ### ${root.size}")
    if (root.size <= limit) {
        result.value += root.size
    }
    root.dirChildren?.let {
        it.forEach { node -> dfsForPart1(node, result, limit) }
    }
}


/**
* For part_2
 **/
fun helperForPart2(root: Node): Long {
    val totalSize = 70000000
    val needSize = 30000000
    val currentSize = root.size
    val minDeleteSize = needSize - (totalSize - currentSize)

    if (minDeleteSize <= 0) {
        return 0
    }
    val result = Reference<Long>(currentSize)
    dfsForPart2(root, result, minDeleteSize)
    return result.value
}

fun dfsForPart2(root: Node, result: Reference<Long>, minDeleteSize: Long) {
    if (root.size >= minDeleteSize) {
        result.value = min(result.value, root.size)
    }
    root.dirChildren?.let {
        it.forEach { node -> dfsForPart2(node, result, minDeleteSize) }
    }
}