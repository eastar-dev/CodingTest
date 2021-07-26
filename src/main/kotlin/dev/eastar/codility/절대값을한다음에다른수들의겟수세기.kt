package dev.eastar.codility

// you can also use imports, for example:
// import kotlin.math.*

// you can write to stdout for debugging purposes, e.g.
// println("this is a debug message")
fun main() {
    println(solution7(intArrayOf(-1,-1)))
}
fun solution7(A: IntArray): Int {
    // write your code in Kotlin 1.3.11 (Linux)
    val num = A
    return num.map{
        kotlin.math.abs(it)
    }.toSet().size
}