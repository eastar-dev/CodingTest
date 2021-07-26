package dev.eastar.codility


fun solution10(X: Int, A: IntArray): Int {
    // write your code in Kotlin 1.3.11 (Linux)
    val num = A
    var answer = -1
    val x =X
    val sum  = mutableSetOf<Int>()

    num.forEachIndexed{ index , it ->
        sum.add(it)
        if(sum.size == x){
            return index + 1
        }

    }


    return answer
}
