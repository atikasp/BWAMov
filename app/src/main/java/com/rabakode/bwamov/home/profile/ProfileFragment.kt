package com.rabakode.bwamov.home.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rabakode.bwamov.R
import com.rabakode.bwamov.home.ticket.TicketActivity
import com.rabakode.bwamov.util.Preferences
import com.rabakode.bwamov.wallet.MyWalletActivity

class ProfileFragment : Fragment() {

    lateinit var preferences: Preferences
    lateinit var tvNama: TextView
    lateinit var tvEmail: TextView
    lateinit var ivProfile: ImageView
    lateinit var tvMyWallet: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        tvNama = requireView().findViewById(R.id.tv_nama)
        tvEmail = requireView().findViewById(R.id.tv_email)
        ivProfile = requireView().findViewById(R.id.iv_profile)
        tvMyWallet = requireView().findViewById(R.id.tv_my_wallet)

        preferences = Preferences(requireContext())
        tvNama.text = preferences.getValues("nama")
        tvEmail.text = preferences.getValues("email")

        Glide.with(this)
            .load(preferences.getValues("url"))
            .apply(RequestOptions.circleCropTransform())
            .into(ivProfile)

        tvMyWallet.setOnClickListener{
            startActivity(Intent(activity, MyWalletActivity::class.java))
        }
    }

}