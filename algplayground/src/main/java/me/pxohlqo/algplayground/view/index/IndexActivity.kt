package me.pxohlqo.algplayground.view.index

import android.graphics.text.LineBreaker
import android.icu.text.CaseMap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import dalvik.system.DexClassLoader
import dalvik.system.PathClassLoader
import me.pxohlqo.algplayground.R
import me.pxohlqo.algplayground.model.BaseSolution
import me.pxohlqo.algplayground.view.playGround.PlayGroundAty
import org.jetbrains.anko.*
import java.io.File

class IndexActivity : AppCompatActivity(), AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun testInPgAty() {
        startActivity(intentFor<PlayGroundAty>().singleTop())
    }

    fun toPlgAty(view: View) {
        startActivity(intentFor<PlayGroundAty>().singleTop())
    }


}
