package me.pxohlqo.algplayground

import me.pxohlqo.algplayground.model.BaseSolution
import me.pxohlqo.algplayground.model.leetcode.*
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

    private fun String.toIntArray(): IntArray {
        return this.removeSurrounding("[", "]").split(Regex(",\\s*")).map { it.toInt() }.toIntArray()
    }

    private fun Array<Int>.toString(): String {
        val resultBuilder = StringBuilder("[")
        this.forEachIndexed { index: Int, t: Int ->
            resultBuilder.append(t)
            if (index == this.size - 1) {
                resultBuilder.append("]")
            } else {
                resultBuilder.append(",")
            }
        }
        return resultBuilder.toString()
    }

    private fun IntArray.toString(): String {
        val resultBuilder = StringBuilder("[")
        this.forEachIndexed { index: Int, t: Int ->
            resultBuilder.append(t)
            if (index == this.size - 1) {
                resultBuilder.append("]")
            } else {
                resultBuilder.append(",")
            }
        }
        return resultBuilder.toString()
    }

    private fun Array<String>.toString(): String {
        val resultBuilder = StringBuilder("[")
        this.forEachIndexed { index: Int, t: String ->
            resultBuilder.append(t)
            if (index == this.size - 1) {
                resultBuilder.append("]")
            } else {
                resultBuilder.append(", ")
            }
        }
        return resultBuilder.toString()
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
        val p= P12()
        val input = 1994
        println(p.myApproach(input))
    }
}
