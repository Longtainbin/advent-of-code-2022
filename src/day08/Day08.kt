package day08

import readInput
import kotlin.math.max

fun main() {
    val input = readInput("inputDay08")
    val matrix = createMatrix(input)


    println(processPart1(matrix))
    println(processPart2(matrix))


}


private fun createMatrix(input: List<String>): Array<IntArray> {
    val row = input.size
    val col = input.first().length
    val result = Array(row) { IntArray(col) }

    for (i in input.indices) {
        for (j in 0 until col) {
            result[i][j] = input[i][j] - '0'
        }
    }
    return result
}

// For Part_1
private fun processPart1(matrix: Array<IntArray>): Int {
    val row = matrix.size
    val col = matrix.first().size
    val matList = mutableListOf<Array<IntArray>>(
        upLookMatrix(matrix),
        downLookMatrix(matrix),
        leftLookMatrix(matrix),
        rightLookMatrix(matrix)
    )
    var count = 0
    for (i in 0 until row) {
        for (j in 0 until col) {
            if (isVisable(matrix, i, j, matList)) {
                count++
            }
        }
    }
    return count
}


private fun isVisable(matrix: Array<IntArray>, i: Int, j: Int, matList: List<Array<IntArray>>): Boolean {
    if (isOnEdge(matrix, i, j)) {
        return true
    }
    for (mat in matList) {
        if (matrix[i][j] > mat[i][j]) {
            return true
        }
    }
    return false
}


// For Part_2
private fun processPart2(matrix: Array<IntArray>): Int {
    val row = matrix.size
    val col = matrix.first().size
    var maxScore = 0
    for (i in 0 until row) {
        for (j in 0 until col) {
            maxScore = max(maxScore, getScore(matrix, i, j))
        }
    }
    return maxScore
}

private fun getScore(matrix: Array<IntArray>, i: Int, j: Int): Int {
    if (isOnEdge(matrix, i, j)) {
        return 0
    }

    var upScore = 0
    for (index in (j - 1) downTo 0) {
        upScore++
        if (matrix[i][index] >= matrix[i][j]) {
            break
        }
    }

    var downScore = 0
    for (index in (j + 1) until matrix.size) {
        downScore++
        if (matrix[i][index] >= matrix[i][j]) {
            break
        }
    }

    var leftScore = 0
    for (index in (i - 1) downTo 0) {
        leftScore++
        if (matrix[index][j] >= matrix[i][j]) {
            break
        }
    }

    var rightScore = 0
    for (index in (i + 1) until matrix.first().size) {
        rightScore++
        if (matrix[index][j] >= matrix[i][j]) {
            break
        }
    }
    return upScore * leftScore * downScore * rightScore
}