package com.android.appdev

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var Title_btn_Startbtn:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Title_btn_Startbtn=findViewById<Button>(R.id.Title_btn_Startbtn)

        Title_btn_Startbtn.setOnClickListener{
            var intent = Intent(this,AccountActivity::class.java)
            startActivity(intent)
        }
    }
}