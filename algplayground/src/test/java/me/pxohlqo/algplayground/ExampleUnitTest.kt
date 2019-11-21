package me.pxohlqo.algplayground

import me.pxohlqo.algplayground.model.leetcode.P1
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
        val num: Int = 1
        println(num.javaClass.simpleName)
        println()

        val numAry: Array<Int> = arrayOf(1, 2, 3)
        println(numAry.javaClass.simpleName)
        println()

        val objAry: Array<Any> = arrayOf(1,2,3)
        println(objAry.javaClass.simpleName)
        println(objAry[0].javaClass.simpleName)
        println()

        objAry[0] = numAry
        println(objAry.javaClass.simpleName)
        objAry.forEach { println(it.javaClass.simpleName) }
    }
}
