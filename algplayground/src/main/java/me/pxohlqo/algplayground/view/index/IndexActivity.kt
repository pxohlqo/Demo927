package me.pxohlqo.algplayground.view.index

import android.graphics.text.LineBreaker
import android.icu.text.CaseMap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import dalvik.system.DexClassLoader
import dalvik.system.PathClassLoader
import kotlinx.android.synthetic.main.activity_main.*
import me.pxohlqo.algplayground.R
import me.pxohlqo.algplayground.model.BaseSolution
import me.pxohlqo.algplayground.model.SolutionLoader
import me.pxohlqo.algplayground.view.playGround.PlayGroundAty
import org.jetbrains.anko.*
import java.io.File

class IndexActivity : AppCompatActivity(), AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        indexListLv.adapter =
            ArrayAdapter<String>(this, R.layout.index_item, R.id.itemTv, SolutionLoader.get(this).getTitles())
        indexListLv.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                toPlgAty(position)
            }
    }

    fun testInPgAty() {
        startActivity(intentFor<PlayGroundAty>().singleTop())
    }

    fun toPlgAty(index: Int) {
        startActivity(intentFor<PlayGroundAty>("index" to index).singleTop())
    }


}
