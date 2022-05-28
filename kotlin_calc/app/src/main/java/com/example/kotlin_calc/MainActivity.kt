package com.example.kotlin_calc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var edit1: EditText;
    lateinit var edit2: EditText
    lateinit var btnAdd: Button;
    lateinit var btnSub: Button;
    lateinit var btnMul: Button;
    lateinit var btnDiv: Button
    lateinit var btnRem: Button
    lateinit var textResult: TextView
    lateinit var num1: String;
    lateinit var num2: String
    var result: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "염동빈_2017305045"

        edit1 = findViewById<EditText>(R.id.Edit1)
        edit2 = findViewById<EditText>(R.id.Edit2)
        btnAdd = findViewById<Button>(R.id.BtnAdd)
        btnSub = findViewById<Button>(R.id.BtnSub)
        btnMul = findViewById<Button>(R.id.BtnMul)
        btnDiv = findViewById<Button>(R.id.BtnDiv)
        btnRem = findViewById<Button>(R.id.BtnRem)

        textResult = findViewById<TextView>(R.id.TextResult)

        btnAdd.setOnTouchListener { view, motionEvent ->
            num1 = edit1.text.toString()
            num2 = edit2.text.toString()
            if(num1.length == 0 || num2.length == 0) {
                Toast.makeText(applicationContext, "두 숫자중 입력이 되지 않은 것이 존재합니다", Toast.LENGTH_SHORT).show()
                false
            }
            else {
                result = num1.toDouble() + num2.toDouble()
                textResult.text = "계산 결과 : " + String.format("%.1f", result)
                false
            }
        }

        btnSub.setOnTouchListener { view, motionEvent ->
            num1 = edit1.text.toString()
            num2 = edit2.text.toString()
            if(num1.length == 0 || num2.length == 0) {
                Toast.makeText(applicationContext, "두 숫자중 입력이 되지 않은 것이 존재합니다", Toast.LENGTH_SHORT).show()
                false
            }
            else {
                result = num1.toDouble() - num2.toDouble()
                textResult.text = "계산 결과 : " + String.format("%.1f", result)
                false
            }
        }

        btnMul.setOnTouchListener { view, motionEvent ->
            num1 = edit1.text.toString()
            num2 = edit2.text.toString()
            if(num1.length == 0 || num2.length == 0) {
                Toast.makeText(applicationContext, "두 숫자중 입력이 되지 않은 것이 존재합니다", Toast.LENGTH_SHORT).show()
                false
            }
            else {
                result = num1.toDouble() * num2.toDouble()
                textResult.text = "계산 결과 : " + String.format("%.1f", result)
                false
            }
        }

        btnDiv.setOnTouchListener { view, motionEvent ->
            num1 = edit1.text.toString()
            num2 = edit2.text.toString()
            if(num1.length == 0 || num2.length == 0) {
                Toast.makeText(applicationContext, "두 숫자중 입력이 되지 않은 것이 존재합니다", Toast.LENGTH_SHORT).show()
                false
            }
            else if(num2 == "0") {
                Toast.makeText(applicationContext, "0으로 나눌 수 없습니다", Toast.LENGTH_SHORT).show()
                false
            }
            else {
                result = num1.toDouble() / num2.toDouble()
                textResult.text = "계산 결과 : " + String.format("%.1f", result)
                false
            }
        }

        btnRem.setOnTouchListener { view, motionEvent ->
            num1 = edit1.text.toString()
            num2 = edit2.text.toString()
            if(num1.length == 0 || num2.length == 0) {
                Toast.makeText(applicationContext, "두 숫자중 입력이 되지 않은 것이 존재합니다", Toast.LENGTH_SHORT).show()
                false
            }
            else if(num2 == "0") {
                Toast.makeText(applicationContext, "0으로 나눌 수 없습니다", Toast.LENGTH_SHORT).show()
                false
            }
            else {
                result = num1.toDouble() % num2.toDouble()
                textResult.text = "계산 결과 : " + String.format("%.1f", result)
                false
            }
        }
    }
}