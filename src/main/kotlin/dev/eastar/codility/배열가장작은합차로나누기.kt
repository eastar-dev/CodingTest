package dev.eastar.codility

fun main() {
    solution1()
}

fun solution1(A: IntArray = intArrayOf(-1000, 1000)): Int {
    val num = A.toList()
    var answer = Integer.MAX_VALUE
    val total = num.sum()
    var left = 0
    var right = total

    println(num.subList(0, num.lastIndex))
    println(num.dropLast(1))

    println()
    println()
    println()

    num.subList(0, num.lastIndex).forEach { now ->
        print(now)
        left = left + now
        right = total - left
        answer = Integer.min(answer, kotlin.math.abs(left - right))
        println("$left, $right , $answer")
        left to right
    }


    println(num)
    println(answer)
    return answer
}