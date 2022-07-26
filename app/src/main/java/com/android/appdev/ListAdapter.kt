package com.android.appdev

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class ListAdapter (private val context: Context, private val listList: ArrayList<ListData>) : RecyclerView.Adapter<ListAdapter.ListViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list,parent,false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listList.size

    override fun onBindViewHolder(holder: ListAdapter.ListViewHolder, position: Int) {
        holder.bind(listList[position],context)

    }

    class ListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        private val list_image = itemView.findViewById<ImageView>(R.id.List_item_image)
        private val list_title = itemView.findViewById<TextView>(R.id.List_item_title)
        private val list_progressbar = itemView.findViewById<ProgressBar>(R.id.List_item_progressbar)
        private val list_progress = itemView.findViewById<TextView>(R.id.List_item_progresstv)

        fun bind(listData: ListData, context: Context){
            list_title.text = listData.listtitle.text
            list_progressbar.progress = listData.listprogress.progress
            list_progress.text = listData.listprogresstv.text

        }
    }

}