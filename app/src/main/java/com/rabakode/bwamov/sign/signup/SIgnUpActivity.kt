package com.rabakode.bwamov.sign.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.*
import com.rabakode.bwamov.R
import com.rabakode.bwamov.sign.signin.User
import com.rabakode.bwamov.util.Preferences

class   SIgnUpActivity : AppCompatActivity() {

    private lateinit var sUsername: String
    private lateinit var sPassword: String
    private lateinit var sName: String
    private lateinit var sEmail: String

    lateinit var preferences: Preferences

    private lateinit var mFirebaseInstance: FirebaseDatabase
    private lateinit var mFirebaseDatabase: DatabaseReference
    private lateinit var mDatabaseReference: DatabaseReference
    private lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        preferences = Preferences(this)

        mFirebaseInstance = FirebaseDatabase.getInstance()
        mFirebaseDatabase = mFirebaseInstance.getReference("User")
        mDatabase = FirebaseDatabase.getInstance().getReference()
        mDatabaseReference = mFirebaseInstance.getReference("User")

        val btnNext: Button = findViewById(R.id.btn_next)
        val etUsername: EditText = findViewById(R.id.et_username)
        val etPassword: EditText = findViewById(R.id.et_password)
        val etName: EditText = findViewById(R.id.et_name)
        val etEmail: EditText = findViewById(R.id.et_email)

        btnNext.setOnClickListener {
            sUsername = etUsername.text.toString()
            sPassword = etPassword.text.toString()
            sName = etName.text.toString()
            sEmail = etEmail.text.toString()

            if(sUsername == ""){
                etUsername.error = "Silahkan isi username anda"
                etUsername.requestFocus()
            }
            else if(sPassword == ""){
                etPassword.error = "Silahkan isi password anda"
                etPassword.requestFocus()
            }
            else if(sName == ""){
                etName.error = "Silahkan isi nama anda"
                etName.requestFocus()
            }
            else if(sEmail == ""){
                etEmail.error = "Silahkan isi email anda"
                etEmail.requestFocus()
            }
            else{
                saveUserData(sUsername, sPassword, sName, sEmail)
            }

        }
    }

    private fun saveUserData(sUsername: String, sPassword: String, sName: String, sEmail: String) {
        var user = User()
        user.username = sUsername
        user.password = sPassword
        user.nama = sName
        user.email = sEmail

        if(sUsername != null){
            checkingUsername(sUsername, user)
        }


    }

    private fun checkingUsername(sUsername: String, data: User) {
        mFirebaseDatabase.child(sUsername).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var user = dataSnapshot.getValue(User::class.java)
                if(user == null){
                    mFirebaseDatabase.child(sUsername).setValue(data)

                    preferences.setValue("nama", data.nama.toString())
                    preferences.setValue("user", data.username.toString())
                    preferences.setValue("saldo", data.saldo.toString())
                    preferences.setValue("url", "")//preferences.setValue("password", "")
                    preferences.setValue("email", data.email.toString())
                    preferences.setValue("status", "1")
                    startActivity(Intent(this@SIgnUpActivity, SignUpPhotoActivity::class.java).putExtra("data", data))
                }
                else{
                    Toast.makeText(this@SIgnUpActivity,"User sudah digunakan", Toast.LENGTH_LONG).show()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@SIgnUpActivity,databaseError.message, Toast.LENGTH_LONG).show()
            }

        })

    }
}