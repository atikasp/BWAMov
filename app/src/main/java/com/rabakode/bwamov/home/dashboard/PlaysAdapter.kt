package com.rabakode.bwamov.home.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rabakode.bwamov.R
import com.rabakode.bwamov.model.Plays

class PlaysAdapter(private var data: List<Plays>,
                   private val listener:(Plays) -> Unit) : RecyclerView.Adapter<PlaysAdapter.ViewHolder>() {

    lateinit var contextAdapter: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaysAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView = layoutInflater.inflate(R.layout.row_item_plays, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: PlaysAdapter.ViewHolder, position: Int) {
        holder.bindItem(data[position], listener, contextAdapter)
    }

    override fun getItemCount(): Int =data.size

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){

        var tvTitle: TextView = view.findViewById(R.id.tv_kursi)
        var ivPoster: ImageView = view.findViewById(R.id.iv_poster)

        fun bindItem(data:Plays, listener: (Plays) -> Unit, context: Context){
            tvTitle.setText(data.nama)
            Glide.with(context)
                .load(data.url)
                .apply(RequestOptions.circleCropTransform())
                .into(ivPoster)

            itemView.setOnClickListener{
                listener(data)
            }

        }


    }

}
