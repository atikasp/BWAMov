package com.rabakode.bwamov.home.ticket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rabakode.bwamov.R
import com.rabakode.bwamov.model.Checkout
import com.rabakode.bwamov.model.Film

class TicketActivity : AppCompatActivity() {

    private lateinit var ivPoster: ImageView
    private lateinit var tvJudul: TextView
    private lateinit var tvGenre: TextView
    private lateinit var tvRating: TextView
    private lateinit var rvCheckoutKursi: RecyclerView

    private var datalist = ArrayList<Checkout>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket)

        ivPoster = findViewById(R.id.iv_poster)
        tvJudul = findViewById(R.id.tv_title)
        tvGenre = findViewById(R.id.tv_genre)
        tvRating = findViewById(R.id.tv_rating)
        rvCheckoutKursi = findViewById(R.id.rv_checkout_kursi)


        var data = intent.getParcelableExtra<Film>("data")
        tvJudul.text = data!!.judul
        tvGenre.text = data.genre
        tvRating.text = data.rating

        Glide.with(this)
            .load(data.poster)
            .into(ivPoster)

        rvCheckoutKursi.layoutManager = LinearLayoutManager(this)
        datalist.add(Checkout("C1",""))
        datalist.add(Checkout("C2",""))

        rvCheckoutKursi.adapter = TicketAdapter(datalist){

        }


    }
}