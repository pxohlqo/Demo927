package me.pxohlqo.algplayground.model

import android.content.Context
import dalvik.system.PathClassLoader
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class SolutionLoader private constructor(val context: Context) : AnkoLogger {

    private val solutions = mutableListOf<SolutionInfoItem>()

    init {
        readSolusContent()
    }

    fun getCount(): Int = solutions.size

    fun getTitles(): Array<String> {
        val titles = Array<String>(solutions.size) { "" }
        solutions.forEachIndexed { i, soluItem  -> titles[i] = soluItem.title }
        return titles
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
//        info { solutions }
    }

    fun loadSoluByIndex(soluIndex: Int): WrappedSolutionClass {
        val classLoader = PathClassLoader(SOLUTION_DEX_PATH, context.classLoader)
        return WrappedSolutionClass(classLoader.loadClass(solutions[soluIndex].path))
    }

    class WrappedSolutionClass(val solution: Class<*>) {

        /**
         * @return [String, String] first string for solution return, second string for time casting in millis.
         * */
        private fun callSolve(vararg input: String): Array<String> {
//            println("-=-=-=-=-=-=")
//            solution.superclass?.methods!!.forEach { println(it.name) }
            val solveMethod = solution.superclass!!.getMethod("solve", Array<String>::class.java)
            val caller = solution.newInstance()
            val solveReturn = solveMethod.invoke(caller, input) as Array<String>
//            println("in callSolve ${solveReturn[0]}")
            return solveReturn

        }

        fun solve(vararg input: String): String {
            println("-=-=-=-=-")
            val returnString = StringBuilder("Input: ")
            input.forEach { returnString.append(it) }
            if (input.isEmpty()) returnString.append("NO INPUT")
            val solveResult = callSolve(*input)
            println("in loader solve: ${solveResult[1]}")
            returnString.append("${System.lineSeparator()}Output: ${solveResult[0]}${System.lineSeparator()}Cast time: ${solveResult[1]}ms")
            return returnString.toString()
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

        private var instance: SolutionLoader? = null

        fun get(context: Context): SolutionLoader {
            if (instance == null) {
                synchronized(SolutionLoader::class) {
                    if (instance==null) {
                        instance = SolutionLoader(context)
                    }
                }
            }
            return instance!!
        }
    }
}