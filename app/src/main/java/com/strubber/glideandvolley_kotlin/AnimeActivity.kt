package com.strubber.glideandvolley_kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_anime.*

class AnimeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anime)

        supportActionBar?.hide()

        val name = intent.extras.getString("anime_name")
        val studio = intent.extras.getString("anime_studio")
        val category = intent.extras.getString("anime_category")
        val rating = intent.extras.getString("anime_rating")
        val description = intent.extras.getString("anime_description")
        val imageUrl = intent.extras.getString("anime_img")

        aa_anime_name.text = name
        aa_studio.text = studio
        aa_categorie.text = category
        aa_rating.text = rating
        aa_description.text = description

        val option = RequestOptions().centerCrop().placeholder(android.R.drawable.ic_dialog_alert).error(R.drawable.ic_launcher_background)
        Glide.with(this).load(imageUrl).apply(option).into(aa_thumbnail)

        collap_layout.title = name
    }
}
