package com.example.user.motherearthbinar

import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_cuaca_fragment.view.*
import kotlinx.android.synthetic.main.view_holder_berita1.view.*
import kotlinx.android.synthetic.main.view_holder_gempa.view.*

class AdapterGempa(val data: ArrayList<GempaModel>, val context: Context) : RecyclerView.Adapter<AdapterGempa.cardView1>() {

    //var data = arrayListOf<GempaModel>()
    var mycontext = context
    var status = listOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): cardView1 {
        return cardView1(LayoutInflater.from(parent.context).inflate(R.layout.view_holder_gempa, parent, false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onBindViewHolder(holder: AdapterGempa.cardView1, position: Int) {
        holder.lokasi.text = data[position].lokasi
        holder.waktu.text = data[position].waktu
        holder.sr.text = data[position].magnitude.toString()

        if (status[position] == "aman") {
            holder.bg_card.setBackgroundColor(mycontext.resources.getColor(R.color.gempaAman))
            holder.btnDetail.setBackground(mycontext.resources.getDrawable(R.drawable.ellipse_button_detail_gempa_hijau))
        } else if (status[position] == "bahaya") {
            holder.bg_card.setBackgroundColor(mycontext.resources.getColor(R.color.gempaBahaya))
            holder.btnDetail.setBackground(mycontext.resources.getDrawable(R.drawable.ellipse_button_detail_gempa_kuning))
        } else if (status[position] == "waspada") {
            holder.bg_card.setBackgroundColor(mycontext.resources.getColor(R.color.gempaWaspada))
            holder.btnDetail.setBackground(mycontext.resources.getDrawable(R.drawable.ellipse_button_detail_gempa_merah))
        }

        // intent  ke detail gempa
        holder.btnDetail.setOnClickListener {
            val intent = Intent(mycontext, DetailGempaActivity::class.java)
            intent.putExtra("data",data[position])
            intent.putExtra("status",status[position])
            mycontext.startActivity(intent)
        }
    }


    inner class cardView1(view: View) : RecyclerView.ViewHolder(view) {
        val lokasi: TextView = view.tempat_gempa
        val btnDetail: Button = view.btn_detail
        val bg_card: ConstraintLayout = view.bg_status
        val waktu: TextView = view.tgl_gempa
        val sr : TextView = view.sr
    }


    fun update(status: List<String>) {
        this.status = status
        notifyDataSetChanged()
    }

}
