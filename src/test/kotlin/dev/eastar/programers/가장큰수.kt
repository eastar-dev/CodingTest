@file:Suppress("PackageDirectoryMismatch", "PackageName")

package dev.eastar.hellokotlin.가장큰수

import dev.eastar.hellokotlin.p

fun main() {
    //println(Solution().solution(intArrayOf(6, 10, 2)))
    println(Solution().solution(intArrayOf(3, 30, 34, 5, 9)))
}

class Solution {
    fun solution(numbers: IntArray): String {
        val result = numbers.map {
            "$it"
        }.sortedWith(Comparator { l, r ->
            (r + l).compareTo(l + r)
        }).joinToString("")
            .trimStart('0')
        return if (result.isEmpty()) "0"
        else result
    }
}


//class Solution {
//    fun solution(numbers: IntArray): String {
//        var acc = "" to emptyList<Int>()
//        for (number in numbers) {
//            var items = mutableListOf<Pair<String, List<Int>>>()
//            for (i in 0..acc.second.size) {
//                acc.second.toMutableList().run {
//                    add(i, number)
//                    items.add(joinToString("") to this)
//                }
//            }
//            //items.p
//
//            var max = items.reduce() { acc, i ->
//                if (acc.first < i.first) i else acc
//            }
//
//            //max.p
//
//            acc = max
//        }
//
//
//        return acc.first
//    }
//}