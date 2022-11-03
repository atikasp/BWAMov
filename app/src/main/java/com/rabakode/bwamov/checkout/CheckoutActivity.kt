package com.rabakode.bwamov.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rabakode.bwamov.R
import com.rabakode.bwamov.model.Checkout
import com.rabakode.bwamov.model.Film
import com.rabakode.bwamov.util.Preferences

class CheckoutActivity : AppCompatActivity() {

    //lateinit var tvSaldo: TextView
    lateinit var rvCheckoutKursi: RecyclerView
    lateinit var btnBayar: Button
    lateinit var btnBatal: Button

    private var datalist = ArrayList<Checkout>()
    private var total: Int = 0
    private lateinit var preferences: Preferences//preferences yg dr projek kita

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        //tvSaldo = findViewById(R.id.tv_saldo)
        rvCheckoutKursi = findViewById(R.id.rv_checkout_kursi)
        btnBayar = findViewById(R.id.btn_bayar)
        btnBatal = findViewById(R.id.btn_batal_to_home)

        preferences= Preferences(this)
        datalist = intent.getSerializableExtra("data") as ArrayList<Checkout> /* = java.util.ArrayList<com.rabakode.bwamov.model.Checkout> */

        for (a in datalist.indices){
            total += datalist[a].harga!!.toInt()
        }

        datalist.add(Checkout("Total harus dibayar ",total.toString()))

        rvCheckoutKursi.layoutManager = LinearLayoutManager(this)
        rvCheckoutKursi.adapter = CheckoutAdapter(datalist){

        }

        btnBayar.setOnClickListener{
            startActivity(Intent(this@CheckoutActivity, CheckoutSuccessActivity::class.java))
        }



    }
}
