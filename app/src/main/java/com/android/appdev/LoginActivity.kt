package com.android.appdev

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LoginActivity : AppCompatActivity() {

    lateinit var login_btn_LogIn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_btn_LogIn=findViewById<Button>(R.id.login_btn_LogIn)

        login_btn_LogIn.setOnClickListener{
            var intent = Intent(this,ListActivity::class.java)
            startActivity(intent)
        }
    }
}