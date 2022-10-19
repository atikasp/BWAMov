package com.rabakode.bwamov.sign.signin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.rabakode.bwamov.home.HomeActivity
import com.rabakode.bwamov.R
import com.rabakode.bwamov.sign.signup.SIgnUpActivity
import com.rabakode.bwamov.util.Preferences


class SIgnInActivity : AppCompatActivity() {

    private lateinit var iUsername: String
    private lateinit var iPassword: String

    private lateinit var nDatabase: DatabaseReference
    private lateinit var preferences: Preferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val btnHome: Button = findViewById(R.id.btn_home)
        val btnDaftar: Button = findViewById(R.id.btn_daftar)
        val etUsername: EditText = findViewById(R.id.et_username)
        val etPassword: EditText = findViewById(R.id.et_password)

        nDatabase= FirebaseDatabase.getInstance().getReference("User")
        preferences = Preferences(this)

        preferences.setValue("onBoarding", "1") //agar saat klik next di onboarding
        // kita gaperlu lagi ke situ setelah daftar akun
        //onboarding muncul saat pertama kali diinstall
        if(preferences.getValues("status").equals("1")){
            finishAffinity()
            startActivity(Intent(this@SIgnInActivity, HomeActivity::class.java))
        }


        btnDaftar.setOnClickListener{
            startActivity(Intent(this@SIgnInActivity, SIgnUpActivity::class.java))
        }


        btnHome.setOnClickListener {
            iUsername = etUsername.text.toString()
            iPassword = etPassword.text.toString()


            if(iUsername == ""){
                etUsername.error="Masukkan username anda"
                etUsername.requestFocus() //kursor di username
            }
            else if(iPassword == ""){
                etPassword.error="Masukkan password anda"
                etPassword.requestFocus() //kursor di password
            }
            else{
                pushLogin(iUsername, iPassword)
            }
        }

    }

    private fun pushLogin(iUsername: String, iPassword: String) {
        nDatabase.child(iUsername).addValueEventListener(object  : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var user = dataSnapshot.getValue(User::class.java)// harus var karena datanya berubah
                if(user == null){
                    Toast.makeText(this@SIgnInActivity,"User tidak ditemukan", Toast.LENGTH_LONG).show()
                }
                else{
                    if(user.password.equals(iPassword)){

                        preferences.setValue("nama", user.nama.toString())
                        preferences.setValue("email", user.email.toString())
                        preferences.setValue("username", user.username.toString())
                        preferences.setValue("saldo", user.saldo.toString())
                        preferences.setValue("url", user.url.toString())
                        preferences.setValue("status", "1")

                        startActivity(Intent(this@SIgnInActivity, HomeActivity::class.java))
                    }
                    else{
                        Toast.makeText(this@SIgnInActivity,"Password anda salah", Toast.LENGTH_LONG).show()
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@SIgnInActivity,error.message, Toast.LENGTH_LONG).show()
            }

        })

    }
}