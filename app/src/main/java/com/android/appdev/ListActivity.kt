package com.android.appdev

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ListActivity() : AppCompatActivity() {
    lateinit var dbManager: DBManager
    lateinit var sqlitedb : SQLiteDatabase

    lateinit var menuFab: FloatingActionButton
    lateinit var userFab: FloatingActionButton
    lateinit var addFab: FloatingActionButton

    @SuppressLint("Range")
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

        dbManager = DBManager(this, "bucketlist",null,1)
        sqlitedb = dbManager.readableDatabase

        var cursor : Cursor
        cursor = sqlitedb.rawQuery("SELECT image, process, title FROM bucketlist;",null)

        var num : Int = 0
        while(cursor.moveToNext()){
            val list = mutableListOf<ListData>()
            var image_uri : Uri
            var str_image = cursor.getString(cursor.getColumnIndex("image")).toString()
            image_uri = Uri.parse(str_image)
            var int_process = cursor.getInt(cursor.getColumnIndex("process"))
            var str_title = cursor.getString(cursor.getColumnIndex("title")).toString()
            var str_process = int_process.toString()
            list.add(ListData(image_uri,str_title,int_process,str_process))
        }
        cursor.close()
    }
}