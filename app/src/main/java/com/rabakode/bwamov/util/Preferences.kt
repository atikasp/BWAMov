package com.rabakode.bwamov.util

import android.content.Context
import android.content.SharedPreferences

//class ini berfungsi agar user tidak perlu bolak-balik signin, jadi db user tersimpan otomatis di db
class Preferences(val context: Context) {
    companion object{
        const val USER_PREF = "USER_PREF"
    }

    var sharedPreferences = context.getSharedPreferences(USER_PREF, 0)

    fun setValue(key: String, value: String){
        val editor:SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(key,value)
        editor.apply()
    }
    
    fun getValues(key: String): String? {
        return sharedPreferences.getString(key, "") //kalau datanya ga ada maka isinya string kosong itu
    }
}