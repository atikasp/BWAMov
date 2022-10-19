package com.rabakode.bwamov.model


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Checkout(
    var kursi: String? = "",
    var harga: String? = ""
): Parcelable

