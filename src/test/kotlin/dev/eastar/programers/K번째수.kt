@file:Suppress("PackageDirectoryMismatch", "PackageName")

package dev.eastar.hellokotlin.template.K번째수

import dev.eastar.hellokotlin.p

fun main() {
    println(Solution().solution(intArrayOf(1, 5, 2, 6, 3, 7, 4),
        arrayOf(
            intArrayOf(2, 5, 3),
            intArrayOf(4, 4, 1),
            intArrayOf(1, 7, 3)
        )
    ))
}

class Solution {
    fun solution(array: IntArray, commands: Array<IntArray>): IntArray {
        return commands.map {
            array.copyOfRange(it[0] - 1, it[1])
                .run {
                    //this.toList().p
                    sort()
                    //this.toList().p
                    get(it[2] - 1)
                }
        }.toIntArray()
    }
}