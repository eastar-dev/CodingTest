@file:Suppress("PackageDirectoryMismatch", "PackageName")

package dev.eastar.hellokotlin.타겟넘버

fun main() {
    println(Solution().solution(intArrayOf(1, 1, 1, 1, 1), 3))
}

class Solution {
    fun solution(numbers: IntArray, target: Int): Int {
        return numbers.fold(listOf(0)) { list, i ->
            list.run {
                map { it + i } + map { it - i }
            }
        }.count { it == target }
    }
}

fun solution2(numbers: IntArray, target: Int): Int {
    numbers.forEach() { now ->
        before.forEach {
            after.add(it + now)
            after.add(it - now)
        }
        before.clear()
        before.addAll(after)
        after.clear()
    }
    return before.count { it == target }
}

var before = mutableListOf(0)
val after = mutableListOf<Int>()
