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

class PopulerAdapter(private var listdata : List<DataModel>) : RecyclerView.Adapter<PopulerAdapter.MyViewHolder>() {

    private lateinit var mlistener : onItemClickListener

    interface onItemClickListener{
        fun onItemClicked(position: Int)
    }

    fun setOnItemClickListener(listener : onItemClickListener){
        mlistener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_most_populer,parent,false)
        return MyViewHolder(view,mlistener)
    }

    fun setFiltered(listdata: List<DataModel>){
        this.listdata = listdata
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = listdata.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = listdata[position]
        holder.imageView.setImageResource(data.image)
        holder.heading.text = data.title
        holder.alamat.text = data.alamat
        holder.jumlah.text = data.jumlah_rating
        holder.rating.rating = data.rating.toFloat()
        holder.button.setImageResource(R.drawable.arror_right)

    }


    class MyViewHolder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val imageView : ImageView = itemView.findViewById(R.id.iv_populer)
        val heading : TextView = itemView.findViewById(R.id.tv_headPopuler)
        val alamat : TextView = itemView.findViewById(R.id.tv_alamatPopuler)
        val rating : RatingBar = itemView.findViewById(R.id.rating_populer)
        val jumlah : TextView = itemView.findViewById(R.id.tv_jmlhPopuler)
        val button : ImageView = itemView.findViewById(R.id.btn_detail)

        init {
            itemView.setOnClickListener {
                listener.onItemClicked(adapterPosition)
            }
        }
    }
}