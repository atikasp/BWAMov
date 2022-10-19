package com.rabakode.bwamov.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.rabakode.bwamov.R
import com.rabakode.bwamov.home.HomeActivity

class CheckoutSuccessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout_success)

        val btnTicket: Button = findViewById(R.id.btn_ticket)
        val btnHome: Button = findViewById(R.id.btn_home)

        btnHome.setOnClickListener{
            finishAffinity()
            startActivity(Intent(this@CheckoutSuccessActivity, HomeActivity::class.java))
        }

        btnTicket.setOnClickListener{
            Toast.makeText(this,"Nanti lagi gaes", Toast.LENGTH_LONG).show()
        }


    }
}