package dev.eastar.codility

//숫자를 인덱스라 생각하고 삽입해서 소팅해버림
fun solution_누락된값중양수이고리턴하기(A: IntArray = intArrayOf(1, 3, 6, 4, 1, 2)): Int {
    val checker = BooleanArray(A.size + 1)
    var checkCount = 0
    var num: Int
    for (i in A.indices) {
        num = A[i]
        if (num > 0 && num < checker.size) checker[num] = true //양수 일 때만 체크
    }
    for (i in 1 until checker.size) {
        if (checker[i]) checkCount++ else return i
    }
    return if (checkCount == checker.size - 1) checker.size else 1 //모두 만족하면 그 다음수 리턴 아니면 모두 음수이므로 양의 최소값 1 리턴
}