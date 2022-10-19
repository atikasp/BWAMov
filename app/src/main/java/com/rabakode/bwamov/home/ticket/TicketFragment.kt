package com.rabakode.bwamov.home.ticket

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.rabakode.bwamov.R
import com.rabakode.bwamov.home.dashboard.ComingSoonAdapter
import com.rabakode.bwamov.model.Film
import com.rabakode.bwamov.util.Preferences

class TicketFragment : Fragment() {

    private lateinit var preferences: Preferences
    private lateinit var mDatabase: DatabaseReference
    private var datalist = ArrayList<Film>()
    lateinit var rvTicketToday: RecyclerView
    lateinit var tvTTotalMovies: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ticket, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        preferences = Preferences(requireContext())
        mDatabase = FirebaseDatabase.getInstance().getReference("Film")

        rvTicketToday = requireView().findViewById(R.id.rv_ticket_today)
        tvTTotalMovies = requireView().findViewById(R.id.tv_total_movies)

        rvTicketToday.layoutManager = LinearLayoutManager(context)
        getData()
    }

    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                datalist.clear()
                for (getDataSnapshot in snapshot.children){
                    val film = getDataSnapshot.getValue(Film::class.java)
                    datalist.add(film!!)
                }
                rvTicketToday.adapter = ComingSoonAdapter(datalist){        //pakai comingsoon karena bentuknya sama
                        startActivity(Intent(context, TicketActivity::class.java).putExtra("data", it))
                }

                tvTTotalMovies.setText("${datalist.size} Movies")
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context,""+error.message,Toast.LENGTH_LONG).show()
            }

        })
    }

}