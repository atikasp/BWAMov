package com.rabakode.bwamov.wallet

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.rabakode.bwamov.R

class TopUpActivity : AppCompatActivity() {

    private var status10k: Boolean = false
    private lateinit var btn10k: TextView
    private lateinit var btnTopup: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_up)

        btn10k = findViewById(R.id.tv_10k)

        btnTopup = findViewById(R.id.btn_topup)
        btnTopup.setOnClickListener{

        }

        btn10k.setOnClickListener{
            if(status10k){
                deselectedMoney(btn10k)
            } else
            {
                selectedMoney(btn10k)
            }
        }


    }

    private fun selectedMoney(textView: TextView){
        textView.setTextColor(resources.getColor(R.color.pink))
        textView.setBackgroundResource(R.drawable.shape_line_pink)
        status10k = true

        btnTopup.visibility = View.VISIBLE
    }

    private fun deselectedMoney(textView: TextView){
        textView.setTextColor(resources.getColor(R.color.white))
        textView.setBackgroundResource(R.drawable.shape_line_white)
        status10k = false

        btnTopup.visibility = View.INVISIBLE
    }
}