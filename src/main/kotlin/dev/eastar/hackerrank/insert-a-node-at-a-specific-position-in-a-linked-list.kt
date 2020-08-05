package dev.eastar.hackerrank

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

class SinglyLinkedListNode(nodeData: Int) {
    public var data: Int
    public var next: SinglyLinkedListNode?

    init {
        data = nodeData
        next = null
    }
}

class SinglyLinkedList {
    public var head: SinglyLinkedListNode?
    public var tail: SinglyLinkedListNode?

    init {
        head = null
        tail = null
    }

    public fun insertNode(nodeData: Int) {
        var node = SinglyLinkedListNode(nodeData)

        if (head == null) {
            head = node
        } else {
            tail?.next = node
        }

        tail = node
    }

}

fun printSinglyLinkedList(head: SinglyLinkedListNode?, sep: String) {
    var node = head;

    while (node != null) {
        print(node?.data)
        node = node?.next

        if (node != null) {
            print(sep)
        }
    }
}

// Complete the insertNodeAtPosition function below.

/*
 * For your reference:
 *
 * SinglyLinkedListNode {
 *     data: Int
 *     next: SinglyLinkedListNode
 * }
 *
 */
fun insertNodeAtPosition(llist: SinglyLinkedListNode?, data: Int, position: Int): SinglyLinkedListNode? {
    if (llist == null) {
        return SinglyLinkedListNode(data)
    }

    var prev: SinglyLinkedListNode? = null
    for (i in 0 until position ) {
        prev = llist.next
    }


    prev?.next = SinglyLinkedListNode(data).also {
        it.next = prev?.next
    }
    return llist
}

fun main(args: Array<String>) {
    val scan = Scanner(System.`in`)

    val llistCount = scan.nextLine().trim().toInt()
    val llist = SinglyLinkedList()

    for (i in 0 until llistCount) {
        val llist_item = scan.nextLine().trim().toInt()
        llist.insertNode(llist_item)
    }

    val data = scan.nextLine().trim().toInt()

    val position = scan.nextLine().trim().toInt()

    val llist_head = insertNodeAtPosition(llist?.head, data, position)

    printSinglyLinkedList(llist_head, " ")
}
