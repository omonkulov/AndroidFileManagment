package com.example.diceroller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private var itemsList: List<ItemData>):RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {
    class MyViewHolder(view:View): RecyclerView.ViewHolder(view){
        var itemFileType: TextView = view.findViewById(R.id.item_type_txt)
        var itemFileName: TextView = view.findViewById(R.id.item_name_txt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemsList[position]
        holder.itemFileType.text = if (item.isFolder)  "Folder" else "File"
        holder.itemFileName.text = item.name
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

}