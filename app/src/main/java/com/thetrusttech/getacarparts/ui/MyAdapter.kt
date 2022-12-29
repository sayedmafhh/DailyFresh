package com.thetrusttech.getacarparts.ui

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MyAdapter(private val surahList: List<Surah>, val context: Context) : RecyclerView.Adapter <MyAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.list_item,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.surah.text = surahList[position].name
        Glide
            .with(context)
            .load(surahList[position].imageUrl)
            .centerCrop()
            .into(holder.image);

    }

    override fun getItemCount(): Int {
        return surahList.size
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {

        val surah : TextView = itemView.findViewById(R.id.surah)
        val image : ImageView = itemView.findViewById(R.id.title_image)

    }

}