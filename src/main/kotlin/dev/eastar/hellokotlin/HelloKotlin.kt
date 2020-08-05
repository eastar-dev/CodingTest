@file:Suppress("PackageDirectoryMismatch")

package dev.eastar.hellokotlin.hello

fun main() {
    println(Solution().merge(arrayOf(intArrayOf(1, 3), intArrayOf(2, 6), intArrayOf(8, 10), intArrayOf(15, 18))).contentDeepToString())
}


    fun solution(numbers: IntArray, target: Int): Int {
        return numbers.fold(listOf(0)) { list, i ->
            list.run {
                map { it + i } + map { it - i }
            }
        }.count { it == target }
    }
    
class Solution {
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


//@ExperimentalStdlibApi
//fun solution(intervals: Array<IntArray>): Array<IntArray> {
//    val result: IntArray = intArrayOf()
//
//
//
//
//
//    val list = intervals.toMutableList()
//
//    val intsLeft = list.removeFirst()
//    val intsRight = list.removeFirst()
//    if(intsLeft.intersect(intsRight.toList()).isNotEmpty())
//        intsLeft.toMutableSet().addAll(intsRight.toList())
//    else
//        list.add(intsRight)
//
//
//    return result
//}

//fun solution(n: Int, computers: Array<IntArray>): Int {
//    computers.foldIndexed(mutableListOf<Set<Int>>()) { index, acc, ints ->
//        println(acc.toString())
//        val nets = ints.mapIndexed { index, i ->
//            if (i == 0)
//                index + 1
//            else
//                0
//        }.filterNot {
//            it == 0
//        }
//
//        acc.firstOrNull {
//            it.intersect(nets).isNotEmpty()
//        } ?: acc.addAll(nets)
//
//        ints.forEachIndexed { index, i ->
//            acc.forEach {
//                it.contains(index)
//            }
//        }
//        return@fold acc
//    }
//
//    var answer = 0
//    return answer
//}

fun solution3(numbers: IntArray, target: Int): Int {
    return numbers.fold(listOf(0)) { list, i ->
        list.run {
            map { it + i } + map { it - i }
        }
    }.count { it == target }
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


//fun arrayManipulation(n: Int, queries: Array<Array<Int>>): Long {
//    val sum = mutableMapOf<Int, Long>()
//    for (i in (n / 2)..queries.lastIndex) {
//        queries.forEach {
//            sum[i] = sum.getOrDefault(i, 0L) + if (i in (it[0] - 1) until it[1]) it[2].toLong() else 0L
//        }
//    }
//    for (i in (n / 2) - 1 downTo 0) {
//        queries.forEach {
//            sum[i] = sum.getOrDefault(i, 0L) + if (i in (it[0] - 1) until it[1]) it[2].toLong() else 0L
//        }
//    }
////    println(sum.toString())
//    return sum.values.max()!!
//}

// Complete the arrayManipulation function below.
//fun arrayManipulation(n: Int, queries: Array<Array<Int>>): Long {
//    var maxValue = 0L
//    var minValue = Long.MAX_VALUE
////    val sum = LongArray(n)
//    val sum = mutableMapOf<Int, Long>()
//
//    queries.sortByDescending { it[2] }
//
//    queries.forEach {
//        if (it[2] < minValue && minValue != Long.MAX_VALUE) {
//            sum.forEach { i, l ->
//                if (i in (it[0] - 1) until it[1]) {
//                    sum[i] = sum.getOrDefault(i, 0L) + it[2]
//                    maxValue = Math.max(maxValue, sum.getOrDefault(i, 0L))
//                    minValue = Math.min(minValue, sum.getOrDefault(i, Long.MAX_VALUE))
//                }
//            }
//        } else {
//            for (i in (it[0] - 1) until it[1]) {
//                sum[i] = sum.getOrDefault(i, 0L) + it[2]
//                maxValue = Math.max(maxValue, sum.getOrDefault(i, 0L))
//                minValue = Math.min(minValue, sum.getOrDefault(i, Long.MAX_VALUE))
//            }
//        }
//    }
//    println(maxValue)
//    return maxValue
//}

//Complete the arrayManipulation function below.
fun arrayManipulation(n: Int, queries: Array<Array<Int>>): Long {
    var maxValue = 0L
    var sum = LongArray(n)
    queries.forEach {
        for (i in (it[0] - 1) until it[1]) {
            sum[i] = sum[i] + it[2]
            maxValue = Math.max(maxValue, sum[i])
        }
    }
    return maxValue
}


//loop 카운트 만큼 이동한다.
fun minimumSwaps(arr: Array<Int>): Int {
    var swapC = 0
    var loopedSet = mutableSetOf<Int>()
    for (i in 0..arr.lastIndex) {
        if (loopedSet.contains(arr[i]))
            continue
        val set = findLoop(arr, i)
        swapC += set.size - 1
        loopedSet.addAll(set)
    }
    //println(swapC)
    return swapC
}

private fun findLoop(ar: Array<Int>, startIndex: Int): Set<Int> {
    val round = mutableSetOf<Int>()
    round += ar[startIndex]
    var nextIndex = ar[startIndex] - 1
    while (startIndex != nextIndex) {
        round += ar[nextIndex]
        nextIndex = ar[nextIndex] - 1
    }
    //println(round.toString())
    return round
}

//나를 넘어만 수에 합을 구한다 나를 넘어간놈은 내 앞앞 자리까지 갈수 있다.
fun miniMaxSum(): Unit {
    val arr = arrayOf(256741038, 623958417, 467905213, 714532089, 938071625)


    var sum = 0L
    var min = Int.MAX_VALUE
    var max = Int.MIN_VALUE
    arr.forEach {
        sum += it.toLong()
        min = Math.min(min, it)
        max = Math.max(max, it)
    }

    println("${sum} ${sum}")
    println("${sum - max} ${sum - min}")
}

fun sockMerchant(n: Int, ar: Array<Int>): Int {
    var pairCount = 0;
    val set = mutableSetOf<Int>()
    ar.forEach {
        val size: Int = set.size
        set.add(it)
        if (size == set.size) {
            set.remove(it)
            pairCount++
        }
    }
    return pairCount
}

/** 계곡의 겟수 구하기 */
fun countingValleys(n: Int, s: String): Int {
    var cnt = 0
    var cur = 0
    s.forEachIndexed { index, c ->
        val lastCur = cur
        cur += if (c == 'U') +1 else -1
        if (lastCur == -1 && cur == 0) {
            cnt++
        }
    }
    return cnt
}

// Complete the jumpingOnClouds function below.
fun jumpingOnClouds(c: Array<Int>): Int {
    var jump = 0
    var cur = 0
    while (cur < c.lastIndex) {
        if (c[cur + 2] == 1) {
            cur++
        } else {
            cur += 2
        }
        jump++
    }
    return jump
}

fun repeatedString(s: String, n: Long): Long {

    val ahrCount = s.count { it == 'a' }
    val ahr = n / s.length
    val skajwl = n % s.length
    val skajwlCount = s.take(skajwl.toInt()).count { it == 'a' }

    return ahrCount * ahr + skajwlCount
}


//모래시계의 합이 최대인 값 출력
// Complete the hourglassSum function below.
fun hourglassSum(arr: Array<Array<Int>>): Int {
    var max = Int.MIN_VALUE
    for (y in 0..arr.lastIndex - 2) {
        for (x in 0..arr[0].lastIndex - 2) {
            val sum = (arr[y + 0][x + 0] + arr[y + 0][x + 1] + arr[y + 0][x + 2]
                    + arr[y + 1][x + 1]
                    + arr[y + 2][x + 0] + arr[y + 2][x + 1] + arr[y + 2][x + 2])
            max = Math.max(sum, max)
        }
    }
    return max
}

// Complete the minimumBribes function below.
fun minimumBribes(q: Array<Int>): Unit {
    var swap = 0

    q.forEachIndexed { index, v ->
        if (v > index + 1 + 2) {
            println("Too chaotic")
            return
        }

        for (i in Math.max(0, v - 1 - 2)..index) {
            if (q[i] > v)
                swap++
        }
    }
    println(swap)
}
