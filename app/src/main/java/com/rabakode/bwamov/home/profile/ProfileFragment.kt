package com.rabakode.bwamov.home.profile

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
import com.rabakode.bwamov.util.Preferences

class ProfileFragment : Fragment() {

    lateinit var preferences: Preferences
    lateinit var tvNama: TextView
    lateinit var tvEmail: TextView
    lateinit var ivProfile: ImageView

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

        preferences = Preferences(requireContext())
        tvNama.text = preferences.getValues("nama")
        tvEmail.text = preferences.getValues("email")

        Glide.with(this)
            .load(preferences.getValues("url"))
            .apply(RequestOptions.circleCropTransform())
            .into(ivProfile)
    }

}