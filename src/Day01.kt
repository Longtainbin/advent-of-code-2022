import java.util.PriorityQueue
import kotlin.math.max

fun main() {
    val input = readInput("inputDay01")

    fun part1(input: List<String>): Int {
        val caloriesList = mutableListOf<Int>()
        var maxCalories = 0
        for (str in input) {
            if (str.isEmpty()) {
                val curElfCalories = caloriesList.sum()
                maxCalories = max(maxCalories, curElfCalories)
                caloriesList.clear()
            } else {
                caloriesList.add(str.toInt())
            }
        }
        return maxCalories
    }


    fun part2(input: List<String>): Int {
        val caloriesList = mutableListOf<Int>()
        val minHead = PriorityQueue<Int>(3)

        for (str in input) {
            if (str.isEmpty()) {
                val curElfCalories = caloriesList.sum()
                caloriesList.clear()
                minHead.add(curElfCalories)
                if (minHead.size > 3) {
                    minHead.poll()
                }
            } else {
                caloriesList.add(str.toInt())
            }
        }
        return minHead.sum()
    }


    println(part2(input))


}
