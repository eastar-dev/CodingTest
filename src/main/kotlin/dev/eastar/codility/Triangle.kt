package dev.eastar.codility

import java.util.*


fun main() {
    Fish().solution(intArrayOf(4, 3, 2, 1, 5), intArrayOf(0, 1, 0, 0, 0))
}

class Triangle {
    fun solution(A: IntArray, B: IntArray): Int {
        // println(A)
        // println(B)

        val fish = A
        val direction = B

        //up fish alive
        var alivefishs = 0

        val fishs = fish.mapIndexed { i, fish ->
            fish to direction[i]
        }

        val waitStack = Stack<Pair<Int, Int>>()

        fishs.forEach {
            if (it.second == 1)
                waitStack.push(it)
            else {
                while (!waitStack.isEmpty()) {
                    val fish = waitStack.peek()
                    if (fish.first < it.first) {
                        waitStack.pop()
                    } else {
                        break
                    }
                }
                if (waitStack.isEmpty())
                    alivefishs++
            }
        }
        return alivefishs + waitStack.size
    }
}