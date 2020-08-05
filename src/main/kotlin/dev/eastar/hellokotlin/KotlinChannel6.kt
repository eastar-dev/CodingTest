@file:Suppress("EXPERIMENTAL_API_USAGE")

import dev.eastar.log.Log
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*


fun main() = runBlocking {
    val n5 = n5()

    n5.consumeEach {
        Log.e(it)
    }

    n5.cancel()

}

fun CoroutineScope.n5() = produce {
    for (i in 1..5)
        send(i) // infinite stream of integers starting from 1
}
