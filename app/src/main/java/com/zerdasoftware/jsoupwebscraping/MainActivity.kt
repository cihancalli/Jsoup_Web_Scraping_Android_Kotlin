@file:Suppress("UNCHECKED_CAST")

package com.zerdasoftware.jsoupwebscraping

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var news:ArrayList<New>? = ArrayList()
    private var newsLoad: MutableList<New?> = ArrayList()
    lateinit var newAdapter: NewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        news = intent.getSerializableExtra("NEWS") as ArrayList<New>
        getTenNews(news!!)

        recyclerView.layoutManager = LinearLayoutManager(this)
        newAdapter = NewAdapter(recyclerView,this,newsLoad)
        recyclerView.adapter = newAdapter
    }

    private fun getTenNews(listNews: ArrayList<New>) {
        for (index in 0..9) {
            newsLoad.add(listNews[index])
        }
    }
}