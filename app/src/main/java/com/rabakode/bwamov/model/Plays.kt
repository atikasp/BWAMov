package com.rabakode.bwamov.model


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Plays(
    var nama: String? = "",
    var url: String? = ""
): Parcelable

