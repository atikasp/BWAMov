package com.rabakode.bwamov.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.rabakode.bwamov.R
import com.rabakode.bwamov.model.Checkout
import com.rabakode.bwamov.model.Film

class PilihBangkuActivity : AppCompatActivity() {

    lateinit var tvJudul: TextView
    lateinit var btnBeliTiket: Button
    lateinit var a3: ImageView
    lateinit var a4: ImageView
    var statusA3: Boolean = false
    var statusA4: Boolean = false
    var total: Int = 0

    private var datalist = ArrayList<Checkout>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pilih_bangku)

        tvJudul = findViewById(R.id.tv_judul)
        btnBeliTiket = findViewById(R.id.btn_beli_tiket)
        a3 = findViewById(R.id.a3)
        a4 = findViewById(R.id.a4)

        val data= intent.getParcelableExtra<Film>("data")
        tvJudul.text = data!!.judul

        a3.setOnClickListener{
            if (statusA3){
                a3.setImageResource(R.drawable.ic_rectangle_empty)
                statusA3 = false
                total -= 1
                beliTiket(total)
            } else{
                a3.setImageResource(R.drawable.ic_rectangle_selected)
                statusA3 = true
                total += 1
                beliTiket(total)

                val data = Checkout("A3", "50000")
                datalist.add(data)
            }
        }

        a4.setOnClickListener{
            if (statusA4){
                a4.setImageResource(R.drawable.ic_rectangle_empty)
                statusA4 = false
                total -= 1
                beliTiket(total)
            } else{
                a4.setImageResource(R.drawable.ic_rectangle_selected)
                statusA4 = true
                total += 1
                beliTiket(total)

                val data = Checkout("A4", "50000")
                datalist.add(data)
            }
        }

        btnBeliTiket.setOnClickListener{
            startActivity(Intent(this@PilihBangkuActivity, CheckoutActivity::class.java).putExtra("data",datalist))
        }


    }

    private fun beliTiket(total: Int) {
        //ketika beli salah satu tiket baru tombolnya visible
        if(total == 0){
            btnBeliTiket.setText("Beli Tiket")
            btnBeliTiket.visibility = View.INVISIBLE
        } else{
            btnBeliTiket.setText("Beli Tiket ($total)")
            btnBeliTiket.visibility = View.VISIBLE
        }

    }
}