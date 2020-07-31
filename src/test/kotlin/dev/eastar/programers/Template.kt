@file:Suppress("PackageDirectoryMismatch", "PackageName")

package dev.eastar.hellokotlin.template

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