package com.saeful.uas.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.saeful.uas.Data.DataModel
import com.saeful.uas.R

class RecomAdapter(private var listdata : List<DataModel>) : RecyclerView.Adapter<RecomAdapter.MyViewholder>() {

    private lateinit var mlistener : onItemClickListener

    interface onItemClickListener{
        fun onItemClicked(position: Int)
    }

    fun setOnItemClickListener(listener : onItemClickListener){
        mlistener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewholder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_recommended,parent,false)
        return MyViewholder(view,mlistener)
    }

    fun setFiltered(listdata: List<DataModel>){
        this.listdata = listdata
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = listdata.size

    override fun onBindViewHolder(holder: MyViewholder, position: Int) {
        val data = listdata[position]
        val jmlh = data.jumlah_rating
        holder.imageView.setImageResource(data.image)
        holder.tv_judul.text = data.title
        holder.tv_alamat.text = data.alamat
        holder.tv_jumlah.text = "($jmlh)"
        holder.ratingBar.rating = data.rating.toFloat()
    }

    class MyViewholder(itemView: View,listener : onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.findViewById<ImageView>(R.id.iv_itemRecom)
        val tv_judul = itemView.findViewById<TextView>(R.id.tv_itemRecom)
        val tv_alamat = itemView.findViewById<TextView>(R.id.tv_alamat)
        val tv_jumlah = itemView.findViewById<TextView>(R.id.tv_jmlh)
        val ratingBar = itemView.findViewById<RatingBar>(R.id.rating)

        init {
            itemView.setOnClickListener {
                listener.onItemClicked(adapterPosition)
            }
        }
    }

}