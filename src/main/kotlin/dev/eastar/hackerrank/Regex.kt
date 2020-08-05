package dev.eastar.hackerrank


fun main(args: Array<String>) {
    println(isMatch("aa", "a"))//false
    println(isMatch("aa", "a*"))
    println(isMatch("ab", ".*"))
    println(isMatch("abc", ".*c"))
    println(isMatch("aab", "c*a*b"))
    println(isMatch("mississippi", "mis*is*p*."))//false
}

//println(isMatch(
//"ssi ssipp i",
//"s*i s* p* ."))//false


fun isMatch(text: String, pattern: String): Boolean {
    val patternToken = getPatternToken(pattern)
    return isMatch(text, patternToken)
}

fun isMatch(text: String, pattern: List<String>): Boolean {

    var leftText = text
    for (i in pattern.indices) {
        val token = pattern[i]
        if (token.contains("*")) {

        } else {
            leftText.indexOf(token)
//            if (==)
                leftText = leftText.removeRange(0, token.length)
        }
    }

    return leftText.isEmpty()
}

fun getPatternToken(pattern: String): List<String> {
    val patternToken = mutableListOf<String>()
    var keep: String = ""
    pattern.forEach {
        keep += it
        if (it == '*') {
            if (keep.length - 2 > 0)
                patternToken.add(keep.take(keep.length - 2))
            patternToken.add(keep.takeLast(2))
            keep = ""
        }
    }
    patternToken.add(keep)
    println("$patternToken")
    return patternToken
}
