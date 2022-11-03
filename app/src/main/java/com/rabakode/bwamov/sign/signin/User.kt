package com.rabakode.bwamov.sign.signin

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class User (
    var email:String ?="",
    var nama:String ?="",
    var password:String ?="",
    var saldo:String ?="",
    var url:String ?="",
    var username:String ?=""
): Parcelable
