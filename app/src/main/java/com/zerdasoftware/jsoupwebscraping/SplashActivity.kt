@file:Suppress("DEPRECATION")

package com.zerdasoftware.jsoupwebscraping

import android.annotation.SuppressLint
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity(),IJsoupData {

    private var loader:AsyncTask<Void,Void,ArrayList<News>>? = null
    private val WEB_PAGE:Int = 1
    private val CATEGORY_ID:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loader = LoadNews(this,WEB_PAGE,CATEGORY_ID)
        loader!!.execute()
    }

    override fun getWebData(datas: ArrayList<News>) {
        val intent = Intent(this,MainActivity::class.java)
        intent.putExtra("NEWS",datas)
        startActivity(intent)
        finish()
    }
}