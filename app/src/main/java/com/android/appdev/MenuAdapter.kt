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
    interface ItemClickListener{
        fun onClick(view: View, position: Int)
    }
    private lateinit var itemClickListener: ItemClickListener

    fun setItemClickListener(itemClickListener: ItemClickListener){
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuAdapter.CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_menu,parent,false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int = menuList.size

    override fun onBindViewHolder(holder: MenuAdapter.CustomViewHolder, position: Int) {
        holder.bind(menuList[position],context)

        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it,position)
        }

    }

    class CustomViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        private val menutitle = itemView.findViewById<TextView>(R.id.tv_menu_title)

        fun bind(menuData: MenuData, context: Context){
            menutitle.text = menuData.menulist
        }

    }

}