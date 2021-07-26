package dev.eastar.codility

fun main() {
    solution8(intArrayOf(1,2,3,5,6))
}
// you can also use imports, for example:
// import kotlin.math.*

// you can write to stdout for debugging purposes, e.g.
// println("this is a debug message")

fun solution8(A: IntArray): Int {
    val num = A
    var sum = (1..num.size).sum()
    return num.fold(sum){ acc,it ->
        acc - it
    } + num.size + 1

}

fun solution2(A: IntArray): Int {
    // write your code in Kotlin 1.3.11 (Linux)
    val num = A
    var sum = (1..num.size + 1).sum()
    return num.fold(sum){ acc,it ->
        acc - it
    }
}