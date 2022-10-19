package com.rabakode.bwamov.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.rabakode.bwamov.R
import com.rabakode.bwamov.home.dashboard.DashboardFragment
import com.rabakode.bwamov.home.profile.ProfileFragment
import com.rabakode.bwamov.home.ticket.TicketFragment

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val ivDashboard: ImageView = findViewById(R.id.iv_menu1)
        val ivTicket: ImageView = findViewById(R.id.iv_menu2)
        val ivProfile: ImageView = findViewById(R.id.iv_menu3)

        val fragmentHome = DashboardFragment()
        val fragmentTicket = TicketFragment()
        val fragmentProfile = ProfileFragment()

        setFragment(fragmentHome)// nge-set awal yg muncul fragment dashboard

        ivDashboard.setOnClickListener{
            setFragment(fragmentHome)
            changeIcon(ivDashboard, R.drawable.ic_home_active)
            changeIcon(ivTicket, R.drawable.ic_ticket)
            changeIcon(ivProfile, R.drawable.ic_profile)
        }
        ivTicket.setOnClickListener{
            setFragment(fragmentTicket)
            changeIcon(ivDashboard, R.drawable.ic_home)
            changeIcon(ivTicket, R.drawable.ic_ticket_active)
            changeIcon(ivProfile, R.drawable.ic_profile)
        }
        ivProfile.setOnClickListener{
            setFragment(fragmentProfile)
            changeIcon(ivDashboard, R.drawable.ic_home)
            changeIcon(ivTicket, R.drawable.ic_ticket)
            changeIcon(ivProfile, R.drawable.ic_profile_active)
        }
    }

    //buat nge-load fragment
    private fun setFragment(fragment: Fragment){
        val fragmentManager= supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.layout_frame, fragment)
        fragmentTransaction.commit()
    }

    private fun changeIcon(imageView: ImageView, int: Int){
        imageView.setImageResource(int)


    }
}