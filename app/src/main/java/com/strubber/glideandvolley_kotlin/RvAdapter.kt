package com.strubber.glideandvolley_kotlin

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import android.util.Pair

class RvAdapter(val context:Activity,val list:ArrayList<Anime>) : RecyclerView.Adapter<ViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_row,p0,false)
        val viewHolder = ViewHolder(view)
        viewHolder.linear.setOnClickListener {
            val intent = Intent(context,AnimeActivity::class.java).apply {
                putExtra("anime_name",list[viewHolder.adapterPosition].name)
                putExtra("anime_description",list[viewHolder.adapterPosition].description)
                putExtra("anime_studio",list[viewHolder.adapterPosition].studio)
                putExtra("anime_category",list[viewHolder.adapterPosition].category)
                putExtra("anime_rating",list[viewHolder.adapterPosition].rating)
                putExtra("anime_img",list[viewHolder.adapterPosition].img)
            }
            val option = ActivityOptions.makeSceneTransitionAnimation(context,Pair<View,String>(viewHolder.imgThumb,"image_transition"))
            context.startActivity(intent,option.toBundle())
        }
        return viewHolder
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName.text = list[position].name
        holder.tvRating.text = list[position].rating
        holder.tvCate.text = list[position].category
        holder.tvStudio.text = list[position].studio

        val option = RequestOptions().centerCrop().placeholder(android.R.drawable.ic_dialog_alert).error(R.drawable.ic_launcher_background)
        Glide.with(context).load(list[position].img).apply(option).into(holder.imgThumb)
    }
}
class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvName = itemView.findViewById<TextView>(R.id.rowname)
    val tvCate = itemView.findViewById<TextView>(R.id.categorie)
    val tvRating = itemView.findViewById<TextView>(R.id.rating)
    val tvStudio = itemView.findViewById<TextView>(R.id.studio)
    val imgThumb = itemView.findViewById<ImageView>(R.id.thumbnail)

    val linear = itemView.findViewById<LinearLayout>(R.id.linear)
}