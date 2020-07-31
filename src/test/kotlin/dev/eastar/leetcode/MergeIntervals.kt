package dev.eastar.leetcode

import dev.eastar.BaseLog
import kotlinx.coroutines.InternalCoroutinesApi
import org.junit.Test

/**
 * 56. Merge Intervals
 * https://leetcode.com/problems/merge-intervals/
 */

class MergeIntervals : BaseLog() {

    @InternalCoroutinesApi
    @Test
    fun mapTest() {
        println(
            Solution().merge(arrayOf(intArrayOf(2, 3), intArrayOf(4, 5), intArrayOf(8, 9), intArrayOf(1, 10)))
                .contentDeepToString()
        )
    }
}


class Solution {
    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        val result = mutableListOf<IntArray>()
        val intervalsList = intervals.toMutableList()

        while (intervalsList.size > 0) {
            val intervalsListConsume = mutableListOf<IntArray>()
            var l = intervalsList.removeAt(0)
            while (intervalsList.isNotEmpty()) {
                val r = intervalsList.removeAt(0)
                if (isOverlaps(l, r)) {
                    l = merge(l, r)
                    intervalsList.addAll(intervalsListConsume)
                    intervalsListConsume.clear()
                } else {
                    intervalsListConsume.add(r)
                }
            }
            result.add(l)
            intervalsList.addAll(intervalsListConsume)
        }
        return result.toTypedArray()
    }

    private fun isOverlaps(l: IntArray, r: IntArray): Boolean =
        (l[0] <= r[0] && r[0] <= l[1]) || (l[0] <= r[1] && r[1] <= l[1])
                || (r[0] <= l[0] && l[0] <= r[1]) || (r[0] <= l[1] && l[1] <= r[1])

    private fun merge(l: IntArray, r: IntArray): IntArray =
        intArrayOf(l[0].coerceAtMost(r[0]), l[1].coerceAtLeast(r[1]))
}


//fail case
//Input
//[[1,4],[5,6]]
//Output
//[[1,6]]
//Expected
//[[1,4],[5,6]]

class Solution_fail {
    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        return intervals.fold(sortedSetOf<Int>()) { acc, ints ->
            for (i in ints[0]..ints[1])
                acc.add(i)
            acc
        }.fold(mutableListOf<IntArray>()) { acc, it ->
            when {
                acc.isEmpty() -> acc.add(intArrayOf(it, it))
                it - acc.last()[1] == 1 -> acc.last()[1] = it
                else -> acc.add(intArrayOf(it, it))
            }
            acc
        }.toTypedArray()
    }
}