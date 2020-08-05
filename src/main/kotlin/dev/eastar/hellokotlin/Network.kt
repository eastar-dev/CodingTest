@file:Suppress("PackageDirectoryMismatch")

package dev.eastar.hellokotlin.network

import dev.eastar.hellokotlin.p

fun main() {
    println(Solution().solution(4, arrayOf(
        intArrayOf(1, 1, 0, 0),
        intArrayOf(1, 1, 0, 0),
        intArrayOf(0, 0, 1, 1),
        intArrayOf(0, 0, 1, 1)
    )))
    println(Solution().solution(4, arrayOf(
        intArrayOf(1, 0, 0, 1),
        intArrayOf(0, 1, 1, 0),
        intArrayOf(0, 1, 1, 1),
        intArrayOf(1, 0, 1, 1)
    )))

    println(Solution().solution(3, arrayOf(
        intArrayOf(1, 1, 0),
        intArrayOf(1, 1, 0),
        intArrayOf(0, 0, 1)
    )))

    println(Solution().solution(3, arrayOf(
        intArrayOf(1, 1, 0),
        intArrayOf(1, 1, 1),
        intArrayOf(0, 1, 1)
    )))
}

class Solution {
    fun solution(n: Int, computers: Array<IntArray>): Int {
        val networks = mutableListOf<MutableSet<Int>>()
        var count = 0
        //개별 networks 구성
        for (i in 0 until n) {
            val network = mutableSetOf<Int>()
            computers[i].forEachIndexed { index, i ->
                if (i == 1)
                    network.add(index + 1)
            }
            networks.add(network)
        }
        networks.p

        //전체
        val allNetworks = mutableListOf<Set<Int>>()
        val filter = mutableSetOf<Int>()
        for (netid in 1..n) {
            val network = getNetwork(netid, filter, networks)
            //network.p
            network.takeIf { network.isNotEmpty() }?.let { allNetworks.add(network) }
        }
        return allNetworks.size
    }

    private fun getNetwork(netid: Int, filter: MutableSet<Int>, networks: List<Set<Int>>): Set<Int> {
        if (filter.contains(netid))
            return emptySet()
        filter.add(netid)

        val indexNetwork = networks[netid - 1]
        val network = mutableSetOf<Int>()
        network += indexNetwork
        for (net in indexNetwork) {
            network += getNetwork(net, filter, networks)
        }
        return network
    }
}
