package dev.eastar.codility


fun main() {
//    ([1, 3, 7, 9, 9], [5, 6, 8, 9, 10])
//    ([], [])
//    ([1], [1])
    val actual = solution3(intArrayOf(1, 3, 7, 9, 9), intArrayOf(5, 6, 8, 9, 10))
    println(actual)
}

fun solution3(A: IntArray, B: IntArray): Int {
    // write your code in Kotlin 1.3.11 (Linux)
    val num1 = A
    val num2 = B
    val length = num1.size
    if(length <= 1)
        return length

    var actual = 1
    var right = num2[0]
    (1 until length).forEach{
        if(num1[it] > right){
            actual++
            right = num2[it]
        }
    }


    println(actual)

    return actual
}
