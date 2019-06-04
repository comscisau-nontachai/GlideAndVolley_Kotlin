package com.strubber.glideandvolley_kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val list = ArrayList<Anime>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        callVolley()
    }

    private fun callVolley() {
        val url = "https://gist.githubusercontent.com/comscisau-nontachai/1b3faa9989feb7e4dd8f7e2beb57ffec/raw/f21567c283e6fa69f63f4e73a564209dde3db869/anime.json"
        val request = JsonArrayRequest(Request.Method.GET,url,null,
            Response.Listener {
                for (i in 0 until it.length()){
                    val jsonObj = it.getJSONObject(i)

                    val name = jsonObj.getString("name")
                    val description = jsonObj.getString("description")
                    val rating = jsonObj.getString("Rating")
                    val category = jsonObj.getString("categorie")
                    val studio = jsonObj.getString("studio")
                    val img = jsonObj.getString("img")

                    val data = Anime(name,description,rating,category,studio,img)
                    list.add(data)
                }

                //set adapter
                recycler_view.setHasFixedSize(true)
                recycler_view.layoutManager = LinearLayoutManager(this)
                val adapter = RvAdapter(this,list)
                recycler_view.adapter = adapter
            },
            Response.ErrorListener { Toast.makeText(this, "something went wrong", Toast.LENGTH_SHORT).show() })
        val queue = Volley.newRequestQueue(this)
        queue.add(request)

    }
}
