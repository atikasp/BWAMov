package com.rabakode.bwamov.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.rabakode.bwamov.R
import com.rabakode.bwamov.sign.signin.SIgnInActivity

class OnBoardingThreeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding_three)

        val btnDaftar: Button = findViewById(R.id.btn_daftar)

        btnDaftar.setOnClickListener{
            finishAffinity() //menghilangkan patch sebelumnya jika diklik tombol back
            startActivity(Intent(this, SIgnInActivity::class.java))
        }
    }
}