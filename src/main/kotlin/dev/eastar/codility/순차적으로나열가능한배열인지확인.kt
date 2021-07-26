package dev.eastar.codility

fun main() {
    println(solution4(intArrayOf(4, 1, 3, 2)))
}

fun solution4(A: IntArray): Int {
    // write your code in Kotlin 1.3.11 (Linux)
    // write your code in Kotlin 1.3.11 (Linux)
    val num = A
    val actual = num.foldIndexed(0) { index, acc, it ->
        println("$index $acc $it")
        it - (index + 1) + acc
    }
    println(actual)

    return if(actual == 0) 1 else 0
}

fun solution6(A: IntArray): Int {
    // write your code in Kotlin 1.3.11 (Linux)
    // write your code in Kotlin 1.3.11 (Linux)
    val num = A
    val actual = num.foldIndexed(0) { index, acc, it ->
        // println("$index $acc $it")
        it xor (index + 1) xor acc
    }
    // println(actual)

    return if(actual == 0) 1 else 0
}

fun solution3(A: IntArray): Int {
    // write your code in Kotlin 1.3.11 (Linux)
    val num = A
    return if (num.foldIndexed(0) { index, acc, it ->
            println("$index, $acc $it")
            it - (index + 1) + acc
        } == 0) return 1
    else 0
}