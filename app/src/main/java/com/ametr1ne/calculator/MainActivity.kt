package com.ametr1ne.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_0.setOnClickListener { setTextField("0") }
        btn_1.setOnClickListener { setTextField("1") }
        btn_2.setOnClickListener { setTextField("2") }
        btn_3.setOnClickListener { setTextField("3") }
        btn_4.setOnClickListener { setTextField("4") }
        btn_5.setOnClickListener { setTextField("5") }
        btn_6.setOnClickListener { setTextField("6") }
        btn_7.setOnClickListener { setTextField("7") }
        btn_8.setOnClickListener { setTextField("8") }
        btn_9.setOnClickListener { setTextField("9") }
        sub_btn.setOnClickListener { setTextField("-") }
        add_btn.setOnClickListener { setTextField("+") }
        mul_btn.setOnClickListener { setTextField("*") }
        div_btn.setOnClickListener { setTextField("/") }
        dot_btn.setOnClickListener { setTextField(".") }
        bracket_open_btn.setOnClickListener { setTextField("(") }
        bracket_close_btn.setOnClickListener { setTextField(")") }
        ac_btn.setOnClickListener {
            math_operation.text = ""
            result.text = ""
        }
        delete_btn.setOnClickListener {
            val str = math_operation.text.toString()
            if (str.isNotEmpty())
                math_operation.text = str.substring(0, str.length - 1)
            result.text = ""
        }

        equal_btn.setOnClickListener {
            try {
                val ex = ExpressionBuilder(math_operation.text.toString()).build()
                val result_operation = ex.evaluate()

                val longRes = result_operation.toLong()
                if (result_operation == longRes.toDouble()) {
                    result.text = longRes.toString()
                } else {
                    result.text = result_operation.toString()
                }
            } catch (e:Exception) {
                Log.d("Ошибка", "сообщение: ${e.message}")
            }
        }

    }

    fun setTextField (str:String) {
        if (result.text != "") {
            math_operation.text = result.text
            result.text = ""
        }
        math_operation.append(str)
    }
}