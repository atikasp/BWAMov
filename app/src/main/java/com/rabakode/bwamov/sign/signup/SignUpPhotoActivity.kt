package com.rabakode.bwamov.sign.signup

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.rabakode.bwamov.home.HomeActivity
import com.rabakode.bwamov.R
import com.rabakode.bwamov.util.Preferences
import java.util.*

class SignUpPhotoActivity : AppCompatActivity(), PermissionListener {

    val REQUEST_IMAGE_CAPTURE = 1
    var statusAdd: Boolean = false
    lateinit var filePath: Uri
    lateinit var storage: FirebaseStorage
    lateinit var storageReference: StorageReference
    lateinit var preferences: Preferences

    lateinit var ivProfile: ImageView
    lateinit var btnSave: Button
    lateinit var ivPhoto: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_photo)

        preferences = Preferences(this)
        storage = FirebaseStorage.getInstance()
        storageReference = storage.getReference()

        var tvHelllo: TextView = findViewById(R.id.tv_hello)
        ivPhoto = findViewById(R.id.iv_add_photo)
        ivProfile = findViewById(R.id.iv_profile)
        btnSave  = findViewById(R.id.btn_save)
        val btnHome: Button = findViewById(R.id.btn_home)

        tvHelllo.text = "Selamat datang\n"+intent.getStringExtra("nama")
        ivPhoto.setOnClickListener{
            if(statusAdd){
                statusAdd = false
                btnSave.visibility = View.VISIBLE
                ivPhoto.setImageResource(R.drawable.ic_add_photo)
                ivProfile.setImageResource(R.drawable.user_pick)
            }
            else{
                Dexter.withActivity(this)
                    .withPermission(android.Manifest.permission.CAMERA)
                    .withListener(this)
                    .check()
            }
        }

        btnHome.setOnClickListener{
            finishAffinity()
            startActivity(Intent(this@SignUpPhotoActivity, HomeActivity::class.java))
        }

        btnSave.setOnClickListener{
            //upload foto ke firebase
            if(filePath != null){
                var progressDialog = ProgressDialog(this)
                progressDialog.setTitle("Uploading...")
                progressDialog.show()

                var ref = storageReference.child("images/"+UUID.randomUUID().toString())
                ref.putFile(filePath)
                    .addOnSuccessListener {
                        progressDialog.dismiss()
                        Toast.makeText(this, "Upload selesai", Toast.LENGTH_LONG).show()

                        //url disave
                        ref.downloadUrl.addOnSuccessListener {
                            preferences.setValue("url", it.toString())
                        }

                        finishAffinity()
                        startActivity(Intent(this@SignUpPhotoActivity, HomeActivity::class.java))
                    }
                //kalau ga sukses upload foto
                    .addOnFailureListener {
                        progressDialog.dismiss()
                        Toast.makeText(this, "Upload Gagal", Toast.LENGTH_LONG).show()
                    }
                //untuk memunculkan berapa persen yg sudah diupload
                    .addOnProgressListener {
                        taskSnapshot -> var progress = 100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount
                        progressDialog.setMessage("Upload "+progress.toInt()+" %")
                    }
            }
            else{
                Toast.makeText(this, "File sudah ada", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onPermissionGranted(response: PermissionGrantedResponse?) {
        //kalau disetujuin
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also {
            takePictureIntent -> takePictureIntent.resolveActivity(packageManager).also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }
        }
    }

    override fun onPermissionDenied(response: PermissionDeniedResponse?) {
        Toast.makeText(this, "Anda tidak bisa menambahkan foto. Akses ditolak!", Toast.LENGTH_LONG).show()
    }

    override fun onPermissionRationaleShouldBeShown(
        permission: PermissionRequest?,
        token: PermissionToken?
    ) {
        TODO("Not yet implemented")
    }

    override fun onBackPressed() {
        Toast.makeText(this, "Tergesa? Klik tombil nanti aja", Toast.LENGTH_LONG).show()
    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //untuk pencarian foto
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            var bitmap = data?.extras?.get("data") as Bitmap
            statusAdd = true

            filePath = data.getData()!!
            Glide.with(this)
                .load(bitmap)
                .apply(RequestOptions.circleCropTransform())
                .into(ivProfile)

            btnSave.visibility = View.VISIBLE
            ivPhoto.setImageResource(R.drawable.ic_delete_photo)
        }

    }


}