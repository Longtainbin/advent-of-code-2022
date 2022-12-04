fun main() {

    val input = readInput("inputDay04")

    fun part1(input: List<String>): Int {
        return processPart1(input)
    }


    fun part2(input: List<String>): Int {
        return processPart2(input)
    }

    println(part1(input))
    println(part2(input))


}

// For part_1
private fun processPart1(input: List<String>): Int {
    var sum = 0
    input.forEach {
        val pairArray = transform(it)
        if (pairArray[0].contains(pairArray[1]) || pairArray[1].contains(pairArray[0])) {
            sum++
        }
    }
    return sum
}

/**
 * if this fully contain other, return true;
 * otherwise return false
 */
fun Pair<Int, Int>.contains(other: Pair<Int, Int>): Boolean {
    return (this.first <= other.first) && (this.second >= other.second)
}

// For part_2
private fun processPart2(input: List<String>): Int {
    var sum = 0
    input.forEach {
        val pairArray = transform(it)
        if (pairArray[0].overlap(pairArray[1])) {
            sum++
        }
    }
    return sum
}

/**
 * if two pairs do overlap ,return true;
 * otherwise return false
 */
fun Pair<Int, Int>.overlap(other: Pair<Int, Int>): Boolean {
    return  this.contains(other) || (this.first in other.first..other.second) || (this.second in other.first..other.second)
}

// common function
private fun transform(str: String): Array<Pair<Int, Int>> {
    val strList = str.split(',')
    var temp = strList[0].split('-')
    val a = Pair<Int, Int>(temp[0].toInt(), temp[1].toInt())
    temp = strList[1].split('-')
    val b = Pair<Int, Int>(temp[0].toInt(), temp[1].toInt())
    return arrayOf(a, b)
}


