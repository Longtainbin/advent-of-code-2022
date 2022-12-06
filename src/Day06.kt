fun main() {
    val input = readInput("inputDay06")
    val inputStr = input.first()
    fun part1(inputStr: String): Int {
       return help(inputStr,4)
    }


    fun part2(inputStr: String): Int {
        return help(inputStr,14)
    }

    println(part1(inputStr))
    println(part2(inputStr))

}

private fun help(inputStr: String, num: Int): Int {
    val end = inputStr.length - num
    for (index in 0..end) {
        if (!isDuplicate(inputStr.substring(index, index + num).toCharArray())) {
            return index + num
        }
    }
    return 0
}

private fun isDuplicate( cs: CharArray): Boolean {
    val count = mutableSetOf<Char>()
    for (i in cs.indices) {
        count.add(cs[i])
    }
    return cs.size > count.size
}
