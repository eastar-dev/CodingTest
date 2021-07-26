package dev.eastar.hackerrank

/**
https://www.hackerrank.com/challenges/balanced-brackets/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=stacks-queues
 */

import java.io.*
import java.math.*
import java.security.*
import java.text.*
import java.util.*
import java.util.concurrent.*
import java.util.function.*
import java.util.regex.*
import java.util.stream.*
import kotlin.collections.*
import kotlin.comparisons.*
import kotlin.io.*
import kotlin.jvm.*
import kotlin.jvm.functions.*
import kotlin.jvm.internal.*
import kotlin.ranges.*
import kotlin.sequences.*
import kotlin.text.*

// Complete the isBalanced function below.
fun isBalanced(s: String): String {
    var stack = Stack<Char>()

    s.forEach {
        when (it) {
            '{', '(', '[' -> stack.add(it)
            '}' -> if (stack.isEmpty() || stack.pop() != '{') return "NO"
            ')' -> if (stack.isEmpty() || stack.pop() != '(') return "NO"
            ']' -> if (stack.isEmpty() || stack.pop() != '[') return "NO"
        }
    }
    //마지막에 남았는지?
    return if (stack.isEmpty()) "YES" else "NO"
}

fun main(args: Array<String>) {
    val scan = Scanner(System.`in`)

    val t = scan.nextLine().trim().toInt()

    for (tItr in 1..t) {
        val s = scan.nextLine()

        val result = isBalanced(s)

        println(result)
    }
}


/*
16
[()][{}()][](){}([{}(())([[{}]])][])[]([][])(){}{{}{[](){}}}()[]({})[{}{{}([{}][])}]
[()][{}[{}[{}]]][]{}[]{}[]{{}({}(){({{}{}[([[]][[]])()]})({}{{}})})}
(])[{{{][)[)])(]){(}))[{(})][[{)(}){[(]})[[{}(])}({)(}[[()}{}}]{}{}}()}{({}](]{{[}}(([{]
){[]()})}}]{}[}}})}{]{](]](()][{))])(}]}))(}[}{{)}{[[}[]
}(]}){
((]()(]([({]}({[)){}}[}({[{])(]{()[]}}{)}}]]{({)[}{(
{}{({{}})}[][{{}}]{}{}(){{}[]}{}([[][{}]]())
(){}[()[][]]{}(())()[[([])][()]{}{}(({}[]()))()[()[{()}]][]]
()([]({}[]){}){}{()}[]{}[]()(()([[]]()))()()()[]()(){{}}()({[{}][]}[[{{}({({({})})})}]])
[]([{][][)(])}()([}[}(})}])}))]](}{}})[]({{}}))[])(}}[[{]{}]()[(][])}({]{}[[))[[}[}{(]})()){{(]]){][
{()({}[[{}]]()(){[{{}{[[{}]{}((({[]}{}()[])))]((()()))}(()[[[]]])((()[[](({([])()}))[]]))}]})}
()(){{}}[()()]{}{}
{}()([[]])({}){({[][[][[()]]{{}[[]()]}]})}[](())((())[{{}}])
{}(((){}){[]{{()()}}()})[]{{()}{(){()(){}}}}{()}({()(()({}{}()((()((([])){[][{()}{}]})))))})
][[{)())))}[)}}}}[{){}()]([][]){{{{{[)}]]{([{)()][({}[){]({{
{{}(


import java.io.*
import java.math.*
import java.security.*
import java.text.*
import java.util.*
import java.util.concurrent.*
import java.util.function.*
import java.util.regex.*
import java.util.stream.*
import kotlin.collections.*
import kotlin.comparisons.*
import kotlin.io.*
import kotlin.jvm.*
import kotlin.jvm.functions.*
import kotlin.jvm.internal.*
import kotlin.ranges.*
import kotlin.sequences.*
import kotlin.text.*

/*
 * Complete the 'perfectSubstring' function below.
 *
 * The function is expected to return an INTEGER.

 A string s comprised of digits from 0 to 9 contains a perfect substring
 if all the elements within a substring occur exactly k times.
 Calculate the number of perfect substrings in s.
 */

fun perfectSubstring(): Int {
    int count =0

    return count
}

fun perfectString(text : String ): Int{

}


fun main(args: Array<String>) {

    println(perfectString("1102021222", 2) == 6)
    println(perfectString("1020122", 2) == 2)
    println(perfectString("1221221121", 3) == 3)

    val result = perfectSubstring()

    println(result)
}



 Theo Kim  eastar jeong
Autocomplete Ready
Task Description
A string s comprised of digits from 0 to 9 contains a perfect substring if all the elements within a substring occur exactly k times. Calculate the number of perfect substrings in s.





Example

s = 1102021222

k = 2

The 6 perfect substrings are:

s[0:1] = 11
s[0:5] = 110202
s[1:6] = 102021
s[2:5] = 0202
s[7:8] = 22
s[8:9] = 22


Function Description

Complete the function perfectSubstring in the editor below.



perfectSubstring has the following parameters:

    str s: a string where the value of each element s[i] is described by the character at position i (where 0 ≤ i < n)

    int k: an integer that denotes the required frequency of the substring

Output

    int:  an integer that represents the number of perfect substrings in the given string



Constraints

1 ≤ sizeof(s) ≤ 105
0 ≤ s[i] ≤ 9 (where 0 ≤ i < n)
1 ≤ k ≤ 105


Sample Case 0
Sample Input For Custom Testing

STDIN         Function
-----         --------
1020122   →   s = '1020122'
2         →   k = 2
Sample Output

2
Explanation



Perfect substrings are:

s[0:5] = 102012
s[5:6] = 22



Sample Case 1



Question 1
Question 2
101112131415161718192021222324252627282930313233343536373839404142434445464748495051
import java.io.*
import java.math.*
import java.security.*
import java.text.*
import java.util.*
import java.util.concurrent.*
import java.util.function.*
import java.util.regex.*
import java.util.stream.*
import kotlin.collections.*
…
Line: 51 Col: 1
Input
Output

Run Code
Run Tests
Chat Window

Click on  to join the video room

eastar Joined Video Conference
eastar Left Video Conference
eastar Joined Video Conference
eastar Joined Video Conference
eastar jeong Joined Video Conference
Theo Kim Joined Video Conference
Theo Kim Left Video Conference
eastar jeong Left Video Conference
Type here and press Enter...
load virgil
Char
 */


/**
Task Description
Given an input.py string (s) and a pattern (p), implement regular expression matching with support for'.'and '*'.
'.'Matches any single character.
'*'Matches zero or more of the preceding element.



The matching should cover the entire input.py string (not partial).



Note:
s could be empty and contains only lowercase letters a-z.
p could be empty and contains only lowercase letters a-z, and characters like . or *.
 */