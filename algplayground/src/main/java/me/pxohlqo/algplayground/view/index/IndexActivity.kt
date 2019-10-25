package me.pxohlqo.algplayground.view.index

import android.icu.text.CaseMap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import me.pxohlqo.algplayground.R
import me.pxohlqo.algplayground.model.BaseSolution
import me.pxohlqo.algplayground.view.playGround.PlayGroundAty
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.singleTop
import org.jetbrains.anko.startActivity

class IndexActivity : AppCompatActivity() {

    private val solutionList: List<SolutionDescription> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun testInPgAty() {
        startActivity(intentFor<PlayGroundAty>().singleTop())
    }

    private data class SolutionDescription(val title: String, val description: String)
}
