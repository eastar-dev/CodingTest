package dev.eastar.codility

import java.util.*

fun main() {
    MaxProductOfThree().solution(intArrayOf(4, 3, 2, 1, 5))
}

class MaxProductOfThree {
    fun solution(A: IntArray): Int {
        val given = A
        val sorted = given.sorted()

        val head = sorted.take(2)
        val tail = sorted.takeLast(1)
        // println("${head[0]},${head[1]},${tail[0]}")

        val tail3 = sorted.takeLast(3)
        // println("${tail3[0]},${tail3[1]},${tail3[2]}")




        val result = kotlin.math.max(head[0] * head[1] * tail[0] , tail3[0] * tail3[1] * tail3[2])

        return result
    }
}