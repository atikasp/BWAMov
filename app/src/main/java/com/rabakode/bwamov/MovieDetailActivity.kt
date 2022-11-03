package com.rabakode.bwamov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.database.*
import com.rabakode.bwamov.checkout.PilihBangkuActivity
import com.rabakode.bwamov.home.dashboard.PlaysAdapter
import com.rabakode.bwamov.model.Film
import com.rabakode.bwamov.model.Plays

class MovieDetailActivity : AppCompatActivity() {

    lateinit var tvJudul: TextView
    lateinit var tvGenre: TextView
    lateinit var tvStory: TextView
    lateinit var tvRate: TextView
    lateinit var ivPoster: ImageView
    lateinit var btnPilihBangku: Button
    lateinit var rvActor: RecyclerView


    private lateinit var mDatabase: DatabaseReference
    private var datalist= ArrayList<Plays>() //untuk load foto dan nama actor


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        tvJudul = findViewById(R.id.tv_judul)
        tvGenre = findViewById(R.id.tv_genre)
        tvStory = findViewById(R.id.tv_story)
        tvRate = findViewById(R.id.tv_rating)
        ivPoster = findViewById(R.id.iv_poster)
        btnPilihBangku = findViewById(R.id.btn_pilih_bangku)
        rvActor = findViewById(R.id.rv_actor)

        //ambil data yg sudah dikirim tadi dari dashboard fragmenrt menggunakan model Film dan parameter data
        val data = intent.getParcelableExtra<Film>("data")

        //inisialisasi db
        mDatabase = FirebaseDatabase.getInstance().getReference("Film")
            .child(data!!.judul.toString())
            .child("play")


        tvJudul.text = data.judul
        tvGenre.text = data.genre
        tvRate.text = data.rating
        tvStory.text = data.desc
        Glide.with(this)
            .load(data.poster)
            .into(ivPoster)
        //panggil data who played
        rvActor.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        getData()

        btnPilihBangku.setOnClickListener{
            startActivity(Intent(this@MovieDetailActivity, PilihBangkuActivity::class.java).putExtra("data",data))
        }


    }

    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                datalist.clear()
                for(getDataSnapshot in snapshot.children){
                    var film = getDataSnapshot.getValue(Plays::class.java)
                    datalist.add(film!!)
                }
                rvActor.adapter = PlaysAdapter(datalist){

                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MovieDetailActivity,""+error.message, Toast.LENGTH_LONG).show()
            }

        })
    }
}