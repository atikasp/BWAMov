package com.rabakode.bwamov.model


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Film(
    var desc: String? = "",
    var genre: String? = "",
    var judul: String? = "",
    var poster: String? = "",
    var aktor: String? = "",
    var rating: String? = ""
): Parcelable

