package me.pxohlqo.algplayground.view.playGround

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_play_ground_aty.*
import me.pxohlqo.algplayground.R
import me.pxohlqo.algplayground.model.SolutionLoader

class PlayGroundAty : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_ground_aty)

        val resultString = SolutionLoader(this).loadSoluByIndex(1).solve("1 2 3 4 5")
        resultTv.text = resultString
    }
}
