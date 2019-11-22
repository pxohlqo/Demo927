package me.pxohlqo.algplayground

import me.pxohlqo.algplayground.model.leetcode.P1
import me.pxohlqo.algplayground.model.leetcode.P2
import me.pxohlqo.algplayground.model.leetcode.P3
import me.pxohlqo.algplayground.model.leetcode.P300
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testLeetCodeSolu() {
        val solu = P1()
        val result = solu.solve("[2, 7, 11, 15]", "9")
        println(result.javaClass.simpleName)
        println(result[0])
    }

    @Test
    fun testObjectAry() {
        val aryInt = arrayOf(1, 2, 3).let { println(it.javaClass.simpleName) }
        val intAry = intArrayOf(1, 2, 3).let { println(it.javaClass.simpleName) }

    }

    @Test
    fun testMyApproach() {
        val p3 = P3()
//        val input = "abcabcbb"
        val input = "b"
        println(p3.myApproach(input))
    }
}
