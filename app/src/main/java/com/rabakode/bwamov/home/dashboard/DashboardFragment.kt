package com.rabakode.bwamov.home.dashboard

import android.content.Intent
import android.os.Build.ID
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.database.*
import com.rabakode.bwamov.MovieDetailActivity
import com.rabakode.bwamov.R
import com.rabakode.bwamov.model.Film
import com.rabakode.bwamov.util.Preferences
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class DashboardFragment : Fragment() {

    lateinit var tvNama: TextView
    lateinit var tvSaldo: TextView
    lateinit var ivProfile: ImageView
    lateinit var rvNowplaying: RecyclerView
    lateinit var rvComingsoon: RecyclerView

    private lateinit var preferences: Preferences
    private lateinit var mDatabase: DatabaseReference
    private var datalist =  ArrayList<Film>()


   override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        preferences =
            activity?.let { Preferences(it.applicationContext) }!! //cek disini juga pengganti requireActivity
        mDatabase = FirebaseDatabase.getInstance().getReference("Film")

        tvNama = requireView().findViewById(R.id.tv_nama)
        tvSaldo = requireView().findViewById(R.id.tv_saldo)
        ivProfile = requireView().findViewById(R.id.iv_profile)
        rvNowplaying = requireView().findViewById(R.id.rv_nowplaying)
        rvComingsoon = requireView().findViewById(R.id.rv_comingsoon)

        tvNama.setText(preferences.getValues("nama"))
        if(preferences.getValues("saldo").equals("")){
            currency(preferences.getValues("saldo")!!.toDouble(), tvSaldo)
        }

        Glide
            .with(this)//cek disini penggunaan glide di fragment?
            .load(preferences.getValues("url"))
            .apply(RequestOptions.circleCropTransform())
            .into(ivProfile)

        rvNowplaying.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rvComingsoon.layoutManager = LinearLayoutManager(context)

        getData()
    }

    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                datalist.clear()
                for (getDataSnapshot in dataSnapshot.children){
                    var film = getDataSnapshot.getValue(Film::class.java)
                    if (film != null) {
                        datalist.add(film)
                    }
                }
                rvNowplaying.adapter = NowPlayingAdapter(datalist){
                    //mengirim data ke detail activity
                    startActivity(Intent(context, MovieDetailActivity::class.java).putExtra("data", it))

                }
                rvComingsoon.adapter = ComingSoonAdapter(datalist){
                    startActivity(Intent(context, MovieDetailActivity::class.java).putExtra("data", it))

                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(context,""+databaseError.message, Toast.LENGTH_LONG).show()
            }

        })
    }

    //convert format uang
    private fun currency(harga: Double, text: TextView){
        val localID = Locale("in", ID)
        val formatUang = NumberFormat.getCurrencyInstance(localID)
        text.setText(formatUang.format(harga))

    }

}