package com.ber.exam2android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class Adapter (
    private val click: (id: Long) -> Unit): RecyclerView.Adapter<Adapter.ViewHolder>() {
        private var list: List<Response.Character> = listOf()

        fun setData(list: List<Response.Character>){
            this.list = list
            notifyDataSetChanged()
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
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
                val img = itemView.findViewById<AppCompatImageView>(R.id.imageCharacter)
                val txtStatus = itemView.findViewById<AppCompatTextView>(R.id.status)
                val txtSpecies = itemView.findViewById<AppCompatTextView>(R.id.species)
                val txtLocationName = itemView.findViewById<AppCompatTextView>(R.id.location)
                val name = itemView.findViewById<AppCompatTextView>(R.id.name)
                name.text = item.name

                Glide.with(itemView.context)
                    .load(item.image)
                    .into(img)
                txtStatus.text = item.status
                txtSpecies.text = item.species
                txtLocationName.text = item.location

                itemView.setOnClickListener {
                    click.invoke(item.character_id!!)
                }
            }
        }
}