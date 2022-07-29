package com.android.appdev

import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ShowActivity : AppCompatActivity() {
    lateinit var dbManager: DBManager
    lateinit var sqlitedb : SQLiteDatabase

    lateinit var btnsave : Button
    lateinit var btndelete : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)

        btnsave = findViewById(R.id.Show_button_save)
        btndelete = findViewById(R.id.Show_button_delete)

        btnsave.setOnClickListener {

        }

        btndelete.setOnClickListener {

        }
    }
}