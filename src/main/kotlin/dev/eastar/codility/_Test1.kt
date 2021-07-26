package dev.eastar.codility

import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Paths

// you can also use imports, for example:
// import kotlin.math.*

// you can write to stdout for debugging purposes, e.g.
// println("this is a debug message")
fun main() {
    param()
        .forEach {
            it.forEachIndexed { index, it ->
//                println(index)
//                println(it.contentToString())
                println(solution(it))
            }
        }
}

fun solution(A: IntArray): Int {
    val nums = A
    println(nums.contentToString())

    
    return 0
}

fun solution101(A: IntArray): Int {
    // write your code in Kotlin 1.3.11 (Linux)
    val num = A
    var answer = 0
    return num.map {
        kotlin.math.abs(it)
    }.toSet().size
}

fun solution102(A: IntArray): Int {
    // write your code in Kotlin 1.3.11 (Linux)
    val num = A
    var answer = 0
    return num.map {
        kotlin.math.abs(it)
    }.toSet().size
}

fun solution103(A: IntArray): Int {
    // write your code in Kotlin 1.3.11 (Linux)
    val num = A
    var answer = 0
    return num.map {
        kotlin.math.abs(it)
    }.toSet().size
}

fun solution104(A: IntArray): Int {
    // write your code in Kotlin 1.3.11 (Linux)
    val num = A
    var answer = 0
    return num.map {
        kotlin.math.abs(it)
    }.toSet().size
}

fun solution105(A: IntArray): Int {
    // write your code in Kotlin 1.3.11 (Linux)
    val num = A
    var answer = 0
    return num.map {
        kotlin.math.abs(it)
    }.toSet().size
}


fun param(): List<List<IntArray>> {
    val lines = Files.readAllLines(
        Paths.get("D:\\work\\CodingTest\\src\\main\\kotlin\\dev\\eastar\\codility\\input.py"),
        Charset.defaultCharset()
    )
    val result = lines.map {
//        println(it)
        it.replace(" ", "")
            .split("],[")
            .map { it.trim('(', '[', ')', ']') }
            .filterNot { it.isEmpty() }
            .let {
                if (it.isEmpty())
                    listOf(intArrayOf())
                else
                    it.map {
//                println(it)
                        it.split(",")
                            .map {
//                        println("1 $it")
                                it.replace("![0-9-]".toRegex(), "")
                            }
                            .map {
//                        println("2 $it")
                                it.toInt()
                            }
                    }.map {
                        it.toIntArray()
                    }
            }
    }
//    println(result)
    return result
}