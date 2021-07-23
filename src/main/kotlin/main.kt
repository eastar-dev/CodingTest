fun main(args: Array<String>) {

    solution1(intArrayOf(1,2,3,4) ,2)
}

fun solution1(A: IntArray, K: Int): IntArray {
    val given = A
    lateinit var result : IntArray
    val shift = K % given.size
    val tail = shift
    val head = given.size - shift

    val headList = given.takeLast(tail)
    val tailList = given.take(head)
//    result = headList + tailList

//    given.re

//    result = intArrayOf(1,2,3)

    return result
}
fun solution2(A: IntArray, K: Int): Int {
    val given = A
    var result : Int

    result = given.reduce{ l , r -> l xor r}

    return result
}
