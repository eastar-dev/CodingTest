@file:Suppress("EXPERIMENTAL_API_USAGE")

import dev.eastar.log.Log
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

fun main() = runBlocking {
    var cur = numbersFrom(2)

//    cur.consumeEach {  }

    repeat(20) {
        val prime = cur.receive()
//        Log.e(prime)
        println(prime)

        cur = filter(cur, prime)
//        Log.e(cur)
    }
    coroutineContext.cancelChildren() // cancel all children to let main finish
}

fun CoroutineScope.numbersFrom(start: Int) = produce<Int> {
    var x = start
    while (true) {
//        Log.e(x)
        send(x++) // infinite stream of integers from start
    }
}

fun CoroutineScope.filter(numbers: ReceiveChannel<Int>, prime: Int) = produce<Int> {
//    Log.e(numbers.toList())
    for (x in numbers) {
        Log.e(this , "$x % $prime")
        if (x % prime != 0)
            send(x)
    }
}