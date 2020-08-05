package dev.eastar.hellokotlin

import java.lang.Exception

val <T> T?.p
    get() = this?.also { println(Exception().stackTrace[1].run { ".($fileName:$lineNumber) " } + " " + toString()) }
        ?: println(Exception().stackTrace[1].run { ".($fileName:$lineNumber) " } + " null")

