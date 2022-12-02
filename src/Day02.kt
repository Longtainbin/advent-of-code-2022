fun main() {
    val input = readInput("inputDay02")

    // store shape score
    val SHAPE_SCORE = intArrayOf(1, 2, 3)

    /**
     * OUT_Part1 store result, 0 -> draw
     *  1 -> win ,-1 -> lost
     */
    val OUT_Part1 = arrayOf(intArrayOf(0, 1, -1), intArrayOf(-1, 0, 1), intArrayOf(1, -1, 0))


    val OUT_Part2 = arrayOf(charArrayOf('C', 'A', 'B'), charArrayOf('A', 'B', 'C'), charArrayOf('B', 'C', 'A'))

    fun part1(input: List<String>): Int {
        var totalScore = 0
        for (str in input) {
            val opponentSymbol = str[0]
            val yourSymbol = str[2]
            val outcomeScore: Int = getOutcomeScore(OUT_Part1, opponentSymbol, yourSymbol)
            val shapeScore: Int = getShapeScore(SHAPE_SCORE, yourSymbol)
            totalScore += (shapeScore + outcomeScore)

        }
        return totalScore
    }


    fun part2(input: List<String>): Int {
        var totalScore = 0
        for (str in input) {
            val opponentSymbol = str[0]
            val yourSymbol = str[2]
            val outcomeScore: Int = getOutcomeScore(OUT_Part2, yourSymbol)
            val shapeScore: Int = getShapeScore(SHAPE_SCORE, OUT_Part2, opponentSymbol, yourSymbol)
            totalScore += (shapeScore + outcomeScore)

        }
        return totalScore
    }

    println(part1(input))
    println(part2(input))


}

private fun outcomeScore(outcome: Outcome): Int {
    return if (outcome === Outcome.DRAW) {
        3
    } else if (outcome === Outcome.WIN) {
        6
    } else {
        0
    }
}


/*** For Part_1 ***/
private fun getShapeScore(SHAPE_SCORE: IntArray, yourSymbol: Char): Int {
    return SHAPE_SCORE[yourSymbol - 'X']
}

private fun getOutcomeScore(OUT: Array<IntArray>, opponentSymbol: Char, yourSymbol: Char): Int {
    val i = opponentSymbol - 'A'
    val j = yourSymbol - 'X'
    return if (OUT[i][j] == 0) {
        outcomeScore(Outcome.DRAW)
    } else if (OUT[i][j] == 1) {
        outcomeScore(Outcome.WIN)
    } else {
        outcomeScore(Outcome.LOST)
    }
}

/*** For Part_2 ***/
private fun getShapeScore(SHAPE_SCORE: IntArray, OUT: Array<CharArray>, opponentSymbol: Char, yourSymbol: Char): Int {
    val i: Int = opponentSymbol - 'A'
    val j = yourSymbol - 'X'
    return SHAPE_SCORE[OUT[i][j] - 'A']
}

private fun getOutcomeScore(OUT: Array<CharArray>, yourSymbol: Char): Int {
    return if (yourSymbol == 'Y') {
        outcomeScore(Outcome.DRAW)
    } else if (yourSymbol == 'Z') {
        outcomeScore(Outcome.WIN)
    } else {
        outcomeScore(Outcome.LOST)
    }
}

enum class Outcome {
    WIN,
    LOST,
    DRAW
}