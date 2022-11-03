package com.rabakode.bwamov.wallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rabakode.bwamov.R
import com.rabakode.bwamov.wallet.adapter.WalletAdapter
import com.rabakode.bwamov.wallet.model.Wallet

class MyWalletActivity : AppCompatActivity() {

    private var dataList = ArrayList<Wallet>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_wallet)

        dataList.add(
            Wallet(
                "Gaia",
                "Kamis, 3 April 2021",
                35000.0,
                "0"
            )
        )
        dataList.add(
            Wallet(
                "Drishyam 2",
                "Jumat, 28 Mei 2021",
                35000.0,
                "0"
            )
        )
        dataList.add(
            Wallet(
                "Top Up",
                "Kamis, 27 Mei 2021",
                100000.0,
                "1"
            )
        )
        dataList.add(
            Wallet(
                "Drive My Car",
                "Kamis, 3 Juni 2021",
                50000.0,
                "0"
            )
        )
        dataList.add(
            Wallet(
                "Top Up",
                "Sabtu, 25 April 2021",
                500000.0,
                "1"
            )
        )

        val rvTransaksi : RecyclerView = findViewById(R.id.rv_transaksi)
        rvTransaksi.layoutManager = LinearLayoutManager(this)
        rvTransaksi.adapter = WalletAdapter(dataList){

        }

        val btnTopup : Button = findViewById(R.id.btn_topup)
        btnTopup.setOnClickListener{
            startActivity(Intent(this@MyWalletActivity, TopUpActivity::class.java))
        }


    }
}