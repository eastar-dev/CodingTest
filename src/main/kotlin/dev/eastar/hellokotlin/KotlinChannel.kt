import dev.eastar.log.Log
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

@ExperimentalCoroutinesApi
fun main() = runBlocking {
    val channel = Channel<Int>()
    launch(Dispatchers.Default) {
        for (x in 1..5) {
            delay(1000)
            Log.e(channel.isClosedForReceive, channel.isClosedForSend, channel.isEmpty)

            if (!channel.isClosedForSend) {
                channel.send(x * x)
                if (x == 1)
                    channel.close() // we're done sending
//                if (x == 1)
//                    channel.cancel()
            }
        }
    }

    launch(Dispatchers.Default) {
        delay(1500)
//    channel.close() // we're done sending
        // here we print received values using `for` loop (until the channel is closed)
        for (y in channel)
            println(y)
    }
    println("Done!")
}

