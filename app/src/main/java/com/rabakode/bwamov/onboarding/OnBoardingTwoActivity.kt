package com.rabakode.bwamov.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.rabakode.bwamov.R

class OnBoardingTwoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding_two)

        val btnHome: Button = findViewById(R.id.btn_home)

        btnHome.setOnClickListener{
            startActivity(Intent(this, OnBoardingThreeActivity::class.java))
        }
    }
}