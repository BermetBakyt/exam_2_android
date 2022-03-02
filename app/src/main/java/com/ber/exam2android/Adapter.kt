package com.ber.exam2android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView

class Adapter (
    private val click: (id: Long) -> Unit): RecyclerView.Adapter<Adapter.ViewHolder>() {
        private var list: List<Response.Character> = listOf()

        fun setData(list: List<Response.Character>){
            this.list = list
            notifyDataSetChanged()
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val itemView: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_recycler, parent, false)
            return ViewHolder(itemView, click)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = list[position]
            holder.bind(item)
        }


        override fun getItemCount(): Int {
            return list.size
        }

        class ViewHolder(
            itemView: View,
            private val click: (id: Long) -> Unit
        ) : RecyclerView.ViewHolder(itemView) {

            fun bind(item: Response.Character) {
                val txt = itemView.findViewById<AppCompatTextView>(R.id.item_txt)
                txt.text = item.name
                itemView.setOnClickListener {
                    click.invoke(item.character_id!!)
                }
            }
        }
}