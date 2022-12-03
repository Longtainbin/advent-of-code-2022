fun main() {
    val input = readInput("inputDay03")

    fun part1(input: List<String>): Int {
        return input.sumOf { processPart1(it) }
    }


    fun part2(input: List<String>): Int {
        val n = 3
        val groups = input.size / n
        var sum = 0
        for (group in 1..groups) {
            val startIndex = (group - 1) * n
            sum += processPart2(input[startIndex], input[startIndex + 1], input[startIndex + 2])
        }
        return sum
    }

    println(part1(input))
    println(part2(input))


}

/*** For Part_1 ***/
private fun processPart1(str: String): Int {
    val N = str.length / 2;
    val userSet = HashSet<Char>();
    var sum = 0
    for (i in N until str.length) {
        userSet.add(str[i])
    }

    for (i in 0 until N) {
        if (userSet.contains(str[i])) {
            sum += getPriority(str[i])
            userSet.remove(str[i])
        }
    }
    return sum
}

/*** For Part_2 ***/

private fun processPart2(str1: String, str2: String, str3: String): Int {
    val set1 = string2Set(str1)
    set1.inner(string2Set(str2))
    set1.inner(string2Set(str3))
    return set1.sumOf { getPriority(it) }
}

private fun string2Set(str: String): MutableSet<Char> {
    val set = HashSet<Char>()
    str.forEach { set.add(it) }
    return set
}

fun <T> MutableSet<T>.inner(other: Set<T>) {
    this.retainAll(other)
}

/*** common function ***/
private fun getPriority(item: Char): Int {
    if (item in 'a'..'z') {
        return item - 'a' + 1;
    }
    return item - 'A' + 27
}

