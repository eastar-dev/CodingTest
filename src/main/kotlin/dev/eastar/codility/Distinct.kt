package dev.eastar.codility

import java.util.*

fun main() {
    Distinct().solution(intArrayOf(4, 3, 2, 1, 5))
}


class Distinct() {
// you can also use imports, for example:
// import kotlin.math.*

// you can write to stdout for debugging purposes, e.g.
// println("this is a debug message")

    fun solution(A: IntArray): Int {
        val given = A

        var unique = mutableSetOf<Int>()

        given.forEach{
            unique.add(it)
            // println( "$it $unique" )
        }


        return unique.size
    }

}