package com.android.appdev

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class MenuAdapter(private val context: Context, private val menuList: ArrayList<MenuData>) : RecyclerView.Adapter<MenuAdapter.CustomViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuAdapter.CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_menu,parent,false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int = menuList.size

    override fun onBindViewHolder(holder: MenuAdapter.CustomViewHolder, position: Int) {
        holder.bind(menuList[position],context)

    }

    class CustomViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        private val menutitle = itemView.findViewById<TextView>(R.id.tv_menu_title)

        fun bind(menuData: MenuData, context: Context){
            menutitle.text = menuData.menulist
        }

        init{
            itemView.setOnClickListener {
                if(menutitle.equals("버킷리스트 추가하기")){
                    val intent = Intent(itemView.context,AddList::class.java)
                    ContextCompat.startActivity(itemView.context,intent,null)
                }else if(menutitle.equals("버킷리스트 삭제하기")){
                    val intent = Intent(itemView.context,ShowActivity::class.java)
                    ContextCompat.startActivity(itemView.context,intent,null)
                }else if(menutitle.equals("메인으로 돌아가기")){
                    val intent = Intent(itemView.context,ListActivity::class.java)
                    ContextCompat.startActivity(itemView.context,intent,null)
                }else if(menutitle.equals("로그아웃")){
                    val intent = Intent(itemView.context,ShowActivity::class.java)
                    ContextCompat.startActivity(itemView.context,intent,null)
                }
            }
        }
    }

}