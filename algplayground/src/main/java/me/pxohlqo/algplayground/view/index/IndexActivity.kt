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

        findDemoSolu()
    }

    fun testInPgAty() {
        startActivity(intentFor<PlayGroundAty>().singleTop())
    }

    private data class SolutionDescription(val title: String, val description: String)

    fun findDemoSolu() {
        val codePath = this.packageCodePath
        val demoPath = "me.pxohlqo.algplayground.model.misc.demo.DemoSolu"
        info { "===========codePath: $codePath" }
        val clazz = PathClassLoader(demoPath, this.classLoader).loadClass(demoPath)
        val method = clazz.getDeclaredMethod("solve")
        info { "===========" + method.invoke(clazz.newInstance())}
    }

//    fun findSolus() {
//        val soluContentsPath =
//    }
}
