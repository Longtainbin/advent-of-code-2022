package day08

import kotlin.math.max

fun upLookMatrix(matrix: Array<IntArray>): Array<IntArray> {
    val row = matrix.size
    val col = matrix.first().size
    val result = Array(row) { IntArray(col) }
    for (j in 0 until col) {
        result[0][j] = matrix[0][j]
        var h = result[0][j]
        for (i in 1 until row) {
            result[i][j] = max(result[i - 1][j], h)
            h = max(result[i][j],matrix[i][j])
        }
    }
    return result
}

fun downLookMatrix(matrix: Array<IntArray>): Array<IntArray> {
    val row = matrix.size
    val col = matrix.first().size
    val result = Array(row) { IntArray(col) }
    for (j in 0 until col) {
        result[row - 1][j] = matrix[row - 1][j]
        var h = result[row - 1][j]
        for (i in (row - 2) downTo 0) {
            result[i][j] = max(result[i + 1][j], h)
            h = max(result[i][j],matrix[i][j])
        }
    }
    return result
}

fun leftLookMatrix(matrix: Array<IntArray>): Array<IntArray> {
    val row = matrix.size
    val col = matrix.first().size
    val result = Array(row) { IntArray(col) }
    for (i in 0 until row) {
        result[i][0] = matrix[i][0]
        var h = result[i][0]
        for (j in 1 until col) {
            result[i][j] = max(result[i][j - 1], h)
            h = max(result[i][j],matrix[i][j])
        }
    }
    return result
}

fun rightLookMatrix(matrix: Array<IntArray>): Array<IntArray> {
    val row = matrix.size
    val col = matrix.first().size
    val result = Array(row) { IntArray(col) }
    for (i in 0 until row) {
        result[i][col - 1] = matrix[i][col - 1]
        var h = result[i][col - 1]
        for (j in (col - 2) downTo 0) {
            result[i][j] = max(result[i][j + 1], h)
            h = max(result[i][j],matrix[i][j])
        }
    }
    return result
}


fun isOnEdge(matrix: Array<IntArray>, i: Int, j: Int): Boolean {
    val row = matrix.size
    val col = matrix.first().size
    return i == 0 || j == 0 || i == row - 1 || j == col - 1
}