package com.android.appdev

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListActivity() : AppCompatActivity() {
    lateinit var menuFab: FloatingActionButton
    lateinit var userFab: FloatingActionButton
    lateinit var addFab: FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        menuFab = findViewById(R.id.List_fAB_menu)
        addFab = findViewById(R.id.List_fAB_Add)

        menuFab.setOnClickListener { v: View? ->
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }

        addFab.setOnClickListener { v : View? ->
            val intent = Intent(this, AddList::class.java)
            startActivity(intent)
        }
    }
}