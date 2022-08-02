package com.android.appdev

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_list.view.*

class ListAdapter (val db : BucketListDataBase, var items:List<BucketList>?)
    : RecyclerView.Adapter<ListAdapter.ListViewHolder>(){

    //리스트 클릭 이벤트
    interface ItemClickListener{
        fun onClick(view: View, position: Int)
    }
    private lateinit var itemClickListener: ItemClickListener

    fun setItemClickListener(itemClickListener: ItemClickListener){
        this.itemClickListener = itemClickListener
    }

    lateinit var mContext: Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list,parent,false)

        mContext = parent.context

        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items!!.size
    }

    fun getItem() : List<BucketList>?{
        return items
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(items!!.get(position),position)

        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it,position)
        }

    }


    inner class ListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        var index : Int?=null

        private val list_image = itemView.findViewById<ImageView>(R.id.List_item_image)
        private val list_title = itemView.findViewById<TextView>(R.id.List_item_title)
        private val list_progressbar = itemView.findViewById<ProgressBar>(R.id.List_item_progressbar)
        private val list_progress = itemView.findViewById<TextView>(R.id.List_item_progresstv)

        fun bind(blist: BucketList, position: Int){
            index = position
            Glide.with(itemView).load(Uri.parse(blist.image)).into(itemView.List_item_image)
            list_title.setText(blist.title)
            list_progressbar.setProgress(blist.progress!!)
            list_progress.setText(blist.progress.toString() + "%")

        }

    }
}
