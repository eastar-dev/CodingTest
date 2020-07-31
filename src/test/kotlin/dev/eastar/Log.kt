package dev.eastar

object Log {
    fun w(vararg args: Any?) {
        println(args.toList().toString())
    }
}