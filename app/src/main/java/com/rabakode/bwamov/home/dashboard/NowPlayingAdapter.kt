package com.rabakode.bwamov.home.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rabakode.bwamov.R
import com.rabakode.bwamov.model.Film

class NowPlayingAdapter(private var data: List<Film>,
                        private val listener:(Film) -> Unit) : RecyclerView.Adapter<NowPlayingAdapter.ViewHolder>() {

    lateinit var contextAdapter: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayingAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView = layoutInflater.inflate(R.layout.row_item_now_playing, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: NowPlayingAdapter.ViewHolder, position: Int) {
        holder.bindItem(data[position], listener, contextAdapter)
    }

    override fun getItemCount(): Int =data.size

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){

        var tvTitle: TextView = view.findViewById(R.id.tv_kursi)
        //var tvTitle: TextView = itemView.findViewById(R.id.tv_kursi)
        var tvGenre: TextView = view.findViewById(R.id.tv_genre)
        var tvRate: TextView = view.findViewById(R.id.tv_rate)
        var ivPoster: ImageView = view.findViewById(R.id.iv_poster)

        fun bindItem(data:Film, listener: (Film) -> Unit, context: Context){
            tvTitle.text = data.judul
            tvGenre.text = data.genre
            tvRate.text = data.rating

            Glide.with(context)
                .load(data.poster)
                .into(ivPoster)

            itemView.setOnClickListener{
                listener(data)
            }

        }


    }

}
