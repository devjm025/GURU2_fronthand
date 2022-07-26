package com.android.appdev

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

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
    }
}