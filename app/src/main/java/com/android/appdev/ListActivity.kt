package com.android.appdev

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.item_list.view.*


class ListActivity() : AppCompatActivity() {

    private var adapter: ListAdapter ?= null

    private var db:BucketListDataBase ?= null

    lateinit var rv_view : RecyclerView
    lateinit var menuFab: FloatingActionButton
    lateinit var userFab: FloatingActionButton
    lateinit var addFab: FloatingActionButton

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        menuFab = findViewById(R.id.List_fAB_menu)
        addFab = findViewById(R.id.List_fAB_Add)

        rv_view = findViewById(R.id.List_RecyclerView_list)

        menuFab.setOnClickListener { v: View? ->
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }

        addFab.setOnClickListener { v : View? ->
            val intent = Intent(this, AddList::class.java)
            startActivity(intent)
        }

        db = BucketListDataBase.getInstance(this)
        rv_view.setHasFixedSize(true)
        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
        rv_view.layoutManager = layoutManager

        db!!.listDao().getAll().observe(this, androidx.lifecycle.Observer{
            adapter = ListAdapter(db!!,it)

            rv_view.adapter = adapter

            adapter!!.setItemClickListener(object : ListAdapter.ItemClickListener{
                override fun onClick(view : View, position: Int){
                    val intent = Intent(this@ListActivity,ShowActivity::class.java)
                    intent.putExtra("title", view.List_item_title.text)
                    startActivity(intent)
                }
            })
        })


    }
}