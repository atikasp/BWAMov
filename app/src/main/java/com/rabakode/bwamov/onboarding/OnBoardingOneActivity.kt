package com.rabakode.bwamov.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.rabakode.bwamov.R
import com.rabakode.bwamov.sign.signin.SIgnInActivity
import com.rabakode.bwamov.util.Preferences

class OnBoardingOneActivity : AppCompatActivity() {

    private lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding_one)

        val btnDaftar: Button = findViewById(R.id.btn_daftar)
        val btnHome: Button = findViewById(R.id.btn_home)
        preferences = Preferences(this)

        if(preferences.getValues("onBoarding").equals("1")){
            finishAffinity()
            startActivity(Intent(this, SIgnInActivity::class.java))
        }

        btnDaftar.setOnClickListener{
            preferences.setValue("onBoarding", "1") //agar saat klik next di onboarding
            // kita gaperlu lagi ke situ setelah daftar akun
            //onboarding muncul saat pertama kali diinstall
            finishAffinity() //menghilangkan patch sebelumnya jika diklik tombol back
            startActivity(Intent(this, SIgnInActivity::class.java))
        }

        btnHome.setOnClickListener{
            startActivity(Intent(this, OnBoardingTwoActivity::class.java))
        }
    }
}