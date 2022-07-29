package com.android.appdev

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import kotlinx.android.synthetic.main.activity_signup.*

class SignUpActivity : AppCompatActivity() {

    lateinit var login_btn_SignUp : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        login_btn_SignUp=findViewById<Button>(R.id.login_btn_SignUp)

        login_btn_SignUp.setOnClickListener{
            var intent = Intent(this,ListActivity::class.java)
            startActivity(intent)
        }


    }
}