package me.pxohlqo.algplayground.model

import java.io.Serializable

abstract class BaseSolution {

    protected open fun inputPreprocess(vararg input: String): Any {
        return input
    }

    protected open fun outputPostprocess(vararg output: Any): String {
        val outputStr = StringBuilder()
        output.forEach { outputStr.append(it) }
        return outputStr.toString()
    }

    open fun solve(vararg input: String): Array<String> {
        val processedInput = inputPreprocess(*input)
//        val startTime = System.currentTimeMillis() // start timer

        val solveResult = performSolve(processedInput)

//        val endTime = System.currentTimeMillis() // stop timer
        val processedOutput = outputPostprocess(solveResult)
        println("processedOutput: ${processedOutput.javaClass.simpleName} $processedOutput")
//        val r = arrayOf(processedOutput, (endTime - startTime).toString())
        val r = arrayOf(processedOutput, "Not implemented...")
//        println("in BaseSolution.solve ${r[1]}")
        return r
    }

    protected abstract fun performSolve(vararg input: Any): Any

    protected fun String.toIntArray(): IntArray {
        return this.removeSurrounding("[", "]").split(", ").map { it.toInt() }.toIntArray()
    }

    protected fun Array<Int>.toString(): String {
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

    protected fun IntArray.toString(): String {
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

    protected fun Array<String>.toString(): String {
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

    protected fun jPrintln(a: Any?) = if (a == null) println("NULL") else println(a)

    protected fun jPrint(a: Any?) = if (a == null) print("NULL ") else print("$a ")

    protected fun jPrintln(a: CharArray) = if (a.isEmpty()) println("EMPTY") else a.forEachIndexed { index, c ->
        if (index == 0) print("[")
        print(c)
        if (index != a.size -1) print(", ") else {
            print("]")
            println()
        }
    }


//    protected boxJavaIntArray()
}