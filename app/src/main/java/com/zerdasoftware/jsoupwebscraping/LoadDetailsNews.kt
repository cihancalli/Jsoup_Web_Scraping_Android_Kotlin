@file:Suppress("DEPRECATION")

package com.zerdasoftware.jsoupwebscraping

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.IOException

class LoadDetailsNews(activity: AppCompatActivity, var urlDetails:String):AsyncTask<Void,Void,ArrayList<String>>() {

    private var details: ArrayList<String> = ArrayList()
    private var loader = activity as ILoadDetails

    override fun doInBackground(vararg p0: Void?): ArrayList<String> {
        try {
            val doc:Document = Jsoup.connect(urlDetails).get()
            val h2Text: String = doc.select("div.news-content__inf").select("h2").text()
            details.add(h2Text)
            //println("h2: $h2Text")
            //get only the text inside the tags "p" and "li"
            val textComplete:Elements = doc.select("div.readingTime").select("p, ul > li")

            for (element in textComplete){
                if (element.text() !=  "")
                    println("text: ${element.text()}")
                    details.add(element.text())
            }

        }catch (e: IOException) { e.printStackTrace()}

        return details
    }

    override fun onPostExecute(result: ArrayList<String>?) {
        loader.getDetails(result!!)
    }
}