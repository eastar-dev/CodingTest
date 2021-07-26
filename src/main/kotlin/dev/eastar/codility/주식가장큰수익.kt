package dev.eastar.codility

import java.lang.Integer.max

fun main() {
    주식가장큰수익.solution3()
}

object 주식가장큰수익 {

    fun solution(A: IntArray = intArrayOf(1, 2, 6, 3, 4, 5)): Int {
        // write your code in Kotlin 1.3.11 (Linux)
        val prices = A
        val maxPrice = prices.foldIndexed(0) { it, acc, index ->
            val first = it
            var max = 0
            (index..prices.lastIndex).forEach { next ->
                max = (next - first).takeIf { it > max } ?: max
            }
            println(max)
            max(acc, max)
        }
        println(maxPrice)
        return maxPrice
    }

    fun solution2(A: IntArray = intArrayOf(23171, 21011, 21123, 21366, 21013, 21367)): Int {

        val prices = A
        var max = 0
        val answer = prices.foldIndexed(max) { index, acc, it ->
            val now = it
            var roundMax = 0
            ((index + 1) until prices.size).forEach { next ->
                roundMax = Integer.max(prices[next] - now, roundMax)
            }

            Integer.max(roundMax, acc)
            println(Integer.max(roundMax, acc))
            Integer.max(roundMax, acc)
        }
        return answer
    }


    //fun solution3(A: IntArray = intArrayOf(23171, 21011, 21123, 21366, 21013, 21367)): Int {
    fun solution3(A: IntArray = intArrayOf(5, 4, 3, 2, 1)): Int {

        val prices = A
        var answer = 0

        prices.fold(prices.firstOrNull() ?: 0) { acc, it ->
            answer = Integer.max(answer, it - acc)
            println("$it , $acc , $answer")
            Integer.min(acc, it)
        }
        return answer
    }
}