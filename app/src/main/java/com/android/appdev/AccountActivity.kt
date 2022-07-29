package com.android.appdev

import android.Manifest.permission_group.STORAGE
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.jar.Manifest

class AccountActivity : AppCompatActivity() {

    lateinit var account_btn_LogIn: Button
    lateinit var account_btn_SignUp:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)




        account_btn_LogIn=findViewById<Button>(R.id.account_btn_LogIn)
        account_btn_SignUp=findViewById<Button>(R.id.account_btn_SignUp)

        account_btn_LogIn.setOnClickListener{
            var intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }


        account_btn_SignUp.setOnClickListener{
            var intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }

        //getAllPhotos()
    }

//    private fun getAllPhotos(){
//        val cursor = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//            null,
//            null,
//            null,
//        MediaStore.Images.ImageColumns.DATE_TAKEN+"DESC")
//
//        if(cursor != null){
//            while(cursor.moveToNext()){
//                val uri = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
//                Log.d("AccountActivity",uri)
//            }
//            cursor.close()
//        }
//    }
}