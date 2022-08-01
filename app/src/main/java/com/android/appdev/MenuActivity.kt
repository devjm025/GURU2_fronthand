package com.android.appdev

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.FieldPosition

class MenuActivity : AppCompatActivity() {
    lateinit var menuRecyclerView: RecyclerView

    var menuList = arrayListOf<MenuData>(
        MenuData("버킷리스트 추가하기",0),
        MenuData("메인으로 돌아가기",1),
        MenuData("로그아웃",2),
        MenuData("마이페이지로 돌아가기",3)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        menuRecyclerView = findViewById(R.id.Menu_RecyclerView_menu)

        val mAdapter = MenuAdapter(this, menuList)
        menuRecyclerView.adapter = mAdapter

        mAdapter.setItemClickListener(object : MenuAdapter.ItemClickListener{
            override fun onClick(view : View, position: Int){
                if(position == 0){
                    val intent = Intent(this@MenuActivity,AddList::class.java)
                    ContextCompat.startActivity(this@MenuActivity,intent,null)
                }else if(position == 1){
                    val intent = Intent(this@MenuActivity,ListActivity::class.java)
                    ContextCompat.startActivity(this@MenuActivity,intent,null)
                }else if(position == 2){
                    val intent = Intent(this@MenuActivity,MainActivity::class.java)
                    ContextCompat.startActivity(this@MenuActivity,intent,null)
                }else if(position == 3){
                    val intent = Intent(this@MenuActivity,MyPage::class.java)
                    ContextCompat.startActivity(this@MenuActivity,intent,null)
                }
            }
        })

        val layout = LinearLayoutManager(this)
        menuRecyclerView.layoutManager = layout
        menuRecyclerView.setHasFixedSize(true)
    }


}