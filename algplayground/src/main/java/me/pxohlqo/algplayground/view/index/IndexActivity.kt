package me.pxohlqo.algplayground.view.index

import android.icu.text.CaseMap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dalvik.system.DexClassLoader
import dalvik.system.PathClassLoader
import me.pxohlqo.algplayground.R
import me.pxohlqo.algplayground.model.BaseSolution
import me.pxohlqo.algplayground.view.playGround.PlayGroundAty
import org.jetbrains.anko.*
import java.io.File

class IndexActivity : AppCompatActivity(), AnkoLogger {

    private val solutionList: List<SolutionDescription> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findSolus()
    }

    fun testInPgAty() {
        startActivity(intentFor<PlayGroundAty>().singleTop())
    }

    private data class SolutionDescription(val title: String, val description: String)

    private fun findSolus() {
        val solutions = mutableListOf<SolutionInfoItem>()
        var soluItem = SolutionInfoItem()
        val iStream = assets.open("solutionsContent/solutions.CONTENT")
        var lineNum = 1
        val lineBuffer = StringBuilder()
        iStream.bufferedReader().forEachLine {
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
                (lineNum==2 && !it.startsWith("DESCRIPTION=")) -> lineBuffer.append(it)
                lineNum==4 -> {
                    solutions.add(soluItem)
                    soluItem = SolutionInfoItem()
                    lineNum = 1
                }
            }
        }
        info { solutions.toString() }

        val solution = PathClassLoader("me.pxohlqo.algplayground.model", this.classLoader)
            .loadClass(solutions[1].path)
        val solveMethod = solution.getDeclaredMethod("solve")
//        info { "============Solve result: ${solveMethod.invoke(solution.newInstance())}" }
    }

    companion object {
        private data class SolutionInfoItem(
            var title: String = "",
            var description: String = "",
            var path: String = ""
        ) {
            override fun toString(): String {
                return "「Solution For $title\nDescription: $description\nPath: $path\n」"
            }
        }
    }
}
