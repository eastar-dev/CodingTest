fun main(args: Array<String>) {
    println(solution(15))
}

fun solution(N: Int): Int {
    var max = 0
    N.toString(2)
        .dropWhile { it == '0' }
        .dropLastWhile { it == '0' }
        .map { it - '0' }
        .fold(0) { total, next ->
            if (next == 0) {
                max = kotlin.math.max(max, total + 1)
                total + 1
            } else {
                0
            }
        }
    return max
}

fun solution1(N: Int): Int {
    // write your code in Kotlin 1.3.11 (Linux)
    val num = N
    var answer = 0

    println(num.toString(2))
    num
        .toString(2)
        .dropWhile { '0' == it }
        .dropLastWhile { '0' == it }
        .fold(0) { acc, it ->
            if (it == '1') {
                answer = kotlin.math.max(answer, acc)
                println(answer)
                0
            } else {
                acc + 1
            }
        }
    return answer
}