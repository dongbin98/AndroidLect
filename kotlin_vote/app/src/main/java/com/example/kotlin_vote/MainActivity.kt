package com.example.kotlin_vote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    lateinit var text1: TextView
    lateinit var text2: TextView
    lateinit var switch: Switch
    lateinit var rGroup1: RadioGroup
    lateinit var rOreo: RadioButton;
    lateinit var rPie: RadioButton
    lateinit var rQ: RadioButton
    lateinit var imgAnd: ImageView
    lateinit var btn1: Button
    lateinit var btn2: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "염동빈/2017305045"

        text1 = findViewById<TextView>(R.id.Text1)
        switch = findViewById<Switch>(R.id.SW)

        text2 = findViewById<TextView>(R.id.Text2)
        rGroup1 = findViewById<RadioGroup>(R.id.Rgroup1)
        rOreo = findViewById<RadioButton>(R.id.Rpie)
        rPie = findViewById<RadioButton>(R.id.Rpie)
        rQ = findViewById<RadioButton>(R.id.Rq)

        imgAnd = findViewById<ImageView>(R.id.imgAndroid)

        btn1 = findViewById<Button>(R.id.exit)
        btn2 = findViewById<Button>(R.id.first)

        // 스위치 온오프
        switch.setOnCheckedChangeListener { _, ischecked ->
            // 체크되면 모두 보이도록 설정
            if (ischecked) {
                text2.visibility = android.view.View.VISIBLE
                rGroup1.visibility = android.view.View.VISIBLE
                imgAnd.visibility = android.view.View.VISIBLE
                btn1.visibility = android.view.View.VISIBLE
                btn2.visibility = android.view.View.VISIBLE

            } else {
                text2.visibility = android.view.View.INVISIBLE
                rGroup1.visibility = android.view.View.INVISIBLE
                imgAnd.visibility = android.view.View.INVISIBLE
                btn1.visibility = android.view.View.INVISIBLE
                btn2.visibility = android.view.View.INVISIBLE
            }
        }

        rGroup1.setOnCheckedChangeListener { radioGroup, item ->
            when (item) {
                R.id.Roreo -> imgAnd.setImageResource(R.drawable.oreo)
                R.id.Rpie -> imgAnd.setImageResource(R.drawable.pie)
                R.id.Rq -> imgAnd.setImageResource(R.drawable.q)
            }
        }
    }

    fun exit(v: View?) {
        finish();
    }

    fun first(v: View?) {
        rGroup1.clearCheck()
        text2.visibility = android.view.View.INVISIBLE
        rGroup1.visibility = android.view.View.INVISIBLE
        imgAnd.visibility = android.view.View.INVISIBLE
        btn1.visibility = android.view.View.INVISIBLE
        btn2.visibility = android.view.View.INVISIBLE
        switch.toggle();
    }
}
