fun main(args: Array<String>) {
    println(solution(15))
}

fun solution(N: Int): Int {
    var max = 0
    N.toString(2)
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