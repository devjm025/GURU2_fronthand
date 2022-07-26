package com.android.appdev

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MenuActivity : AppCompatActivity() {
    lateinit var menuRecyclerView: RecyclerView

    var menuList = arrayListOf<MenuData>(
        MenuData("버킷리스트 추가하기"),
        MenuData("버킷리스트 삭제하기"),
        MenuData("메인으로 돌아가기"),
        MenuData("로그아웃")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        menuRecyclerView = findViewById(R.id.Menu_RecyclerView_menu)

        val mAdapter = MenuAdapter(this, menuList)
        menuRecyclerView.adapter = mAdapter

        val layout = LinearLayoutManager(this)
        menuRecyclerView.layoutManager = layout
        menuRecyclerView.setHasFixedSize(true)
    }


}