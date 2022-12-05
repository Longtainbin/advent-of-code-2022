fun main() {
    val input = readInput("inputDay05")
    val operations = initOperation(input)
    fun part1(input: List<String>): String {
        val stack = initStack(input)
        for (ope in operations) {
            for (count in 1..ope[0]) {
                stack[ope[2] - 1].add(stack[ope[1] - 1].removeLast())
            }
        }
        val sb = StringBuilder(stack.size)
        stack.forEach { sb.append(it.last()) }
        return sb.toString()
    }


    fun part2(input: List<String>): String {
        val stack = initStack(input)
        for (ope in operations) {
            val count = ope[0]
            val source = ope[1] - 1
            val dest = ope[2] - 1
            val tempStack = mutableListOf<Char>()
            for (i  in 1..count) {
                tempStack.add(stack[source].removeLast())
            }
            for (i in 1..count) {
                stack[dest].add(tempStack.removeLast())
            }
        }
        val sb = StringBuilder(stack.size)
        stack.forEach { sb.append(it.last()) }
        return sb.toString()
    }

    println(part1(input))
    println(part2(input))


}


private fun initStack(input: List<String>): Array<MutableList<Char>> {
    var lineIndex = 0;
    for (str in input) {
        if (str.isBlank()) {
            lineIndex--
            break
        }
        lineIndex++
    }
    val n = input[lineIndex].trim().split(" ").last().toInt()
    val result = Array<MutableList<Char>>(n) { arrayListOf() }

    while (lineIndex > 0) {
        lineIndex--
        val str = input[lineIndex].trim()
        var charIndex = 0
        for (i in 1 until str.length step 4) {
            if (str[i] in 'A'..'Z') {
                result[charIndex].add(str[i])
            }
            charIndex++
        }
    }
    return result
}

private fun initOperation(input: List<String>): Array<IntArray> {
    var lineIndex = 0;
    for (str in input) {
        if (str.isBlank()) {
            lineIndex++
            break
        }
        lineIndex++
    }

    val result = Array<IntArray>(input.size - lineIndex) { IntArray(3) }
    for (index in lineIndex until input.size) {
        val strArr = input[index].split(" ")
        val i = index - lineIndex
        result[i][0] = strArr[1].toInt()
        result[i][1] = strArr[3].toInt()
        result[i][2] = strArr[5].toInt()
    }
    return result
}