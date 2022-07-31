package com.android.appdev

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_photo.*

class MainActivity : AppCompatActivity() {

    //lateinit var gif_image: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //gif_image=findViewById(R.id.gif_image)

        //Glide.with(this).load(R.raw.title_star).override(560,560).into()

        var handler = Handler()
        handler.postDelayed( {
            var intent = Intent( this, AccountActivity::class.java)
            startActivity(intent)
        }, 5000)
    }

}