package com.rabakode.bwamov.wallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.rabakode.bwamov.R
import com.rabakode.bwamov.home.HomeActivity

class MyWalletSuccessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_wallet_success)

        val btnWallet: Button = findViewById(R.id.btn_ke_wallet)
        val btnHome: Button = findViewById(R.id.btn_home)

        btnHome.setOnClickListener{
            finishAffinity()
            startActivity(Intent(this@MyWalletSuccessActivity, HomeActivity::class.java))
        }

        btnWallet.setOnClickListener{
            finishAffinity()
            startActivity(Intent(this@MyWalletSuccessActivity, MyWalletActivity::class.java))
        }
    }
}