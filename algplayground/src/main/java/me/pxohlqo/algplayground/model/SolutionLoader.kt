package me.pxohlqo.algplayground.model

import android.content.Context
import dalvik.system.PathClassLoader
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class SolutionLoader(val context: Context) : AnkoLogger {

    val solutions = mutableListOf<SolutionInfoItem>()

    init {
        readSolusContent()
    }

    private fun readSolusContent() {
        val inputStr = context.assets.open(CONTENT_FILE_PATH)
        var lineNum = 1
        val lineBuffer = StringBuilder()
        var soluItem = SolutionInfoItem()
        inputStr.bufferedReader().forEachLine {
            when {
                it.startsWith("SOLUTION=") -> {
                    soluItem.title = it.split("=")[1]
                    lineNum++
                }

                it.startsWith("DESCRIPTION=") -> lineBuffer.append(it.split("=")[1])

                it.startsWith("PATH=") -> {
                    lineNum++
                    soluItem.description = lineBuffer.toString()
                    lineBuffer.clear()
                    soluItem.path = it.split("=")[1]
                    lineNum++
                }

                (lineNum == 2 && !it.startsWith("DESCRIPTION=")) -> lineBuffer.append(System.lineSeparator()).append(
                    it
                )

                lineNum == 4 -> {
                    solutions.add(soluItem)
                    soluItem = SolutionInfoItem()
                    lineNum = 1
                }
            }
        }
        info { solutions }
    }

    fun loadSoluByIndex(soluIndex: Int): WrappedSolutionClass {
        val classLoader = PathClassLoader(SOLUTION_DEX_PATH, context.classLoader)
        return WrappedSolutionClass(classLoader.loadClass(solutions[soluIndex].path))
    }


    class WrappedSolutionClass(val solution: Class<*>) {

        private fun callSolve(vararg arg: String): String {
            val solveMethod = solution.getDeclaredMethod("solve", Array<String>::class.java)
            return solveMethod.invoke(solution.newInstance(), arg)!!.toString()
        }

        /**
         * @return [String, String] first string for solution return, second string for time casting in millis.
         * */
        private fun callSolveWithTimer(vararg args: String): List<String> {
            val solveMethod = solution.getDeclaredMethod("solve", Array<Any>::class.java)
            val caller = solution.newInstance()
            val beforeTime = System.currentTimeMillis()
            val solveReturn = solveMethod.invoke(caller, args)
            val afterTime = System.currentTimeMillis()
            val returnString = solveReturn?.toString() ?: "NO OUTPUT"
            return listOf(returnString, (afterTime - beforeTime).toString())

        }

        fun solve(vararg args: String): String {
            val returnString = "Input: $args"
            if (args.isEmpty()) returnString.plus("NO INPUT")
            val solveResult = callSolveWithTimer(*args)
            returnString.plus("${System.lineSeparator()}Output: ${solveResult[0]}${System.lineSeparator()}Cast time: ${solveResult[1]}ms")
            return returnString
        }

        @Deprecated("Not Implement")
        fun solveOnlyOutput(vararg arg: String): String {
            TODO("Not Implement")
        }

    }

    companion object {
        private const val CONTENT_FILE_PATH = "solutionsContent/solutions.CONTENT"

        private const val SOLUTION_DEX_PATH = "me.pxohlqo.algplayground.model"

        data class SolutionInfoItem(
            var title: String = "",
            var description: String = "",
            var path: String = ""
        ) {
            override fun toString(): String {
                return "${System.lineSeparator()}「Solution For: $title${System.lineSeparator()}Description: $description${System.lineSeparator()}Path: $path」"
            }
        }


    }
}