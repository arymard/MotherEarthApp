package com.example.user.motherearthbinar

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_holder_berita1.view.*

class AdapterBerita(val data: ArrayList<BeritaModel>, val context: Context) : RecyclerView.Adapter<AdapterBerita.cardView1>() {

//    var mydata = arrayListOf<BeritaModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): cardView1 {
        return cardView1(LayoutInflater.from(parent.context).inflate(R.layout.view_holder_berita1, parent, false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: cardView1, position: Int) {
        holder.textJudul.text = data[position].source
        holder.textPara.text = data[position].content
        Picasso.get().load(data[position].gambar).into(holder.gambar)
    }

    inner class cardView1(view: View) : RecyclerView.ViewHolder(view) {
        val textJudul : TextView = view.judul
        val textPara : TextView = view.para
        val gambar : ImageView = view.imggempa
    }

//    fun update(data : ArrayList<BeritaModel>){
//        this.mydata = data
//        notifyDataSetChanged()
//    }
}
