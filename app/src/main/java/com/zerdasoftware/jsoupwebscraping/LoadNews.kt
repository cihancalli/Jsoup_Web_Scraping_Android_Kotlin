@file:Suppress("DEPRECATION")

package com.zerdasoftware.jsoupwebscraping

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.IOException

class LoadNews (activity: AppCompatActivity?, var page:Int) : AsyncTask<Void, Void, ArrayList<New>>() {

    private var news:ArrayList<New> = ArrayList()
    private var loadedData = activity as IJsoupData

    @Deprecated("Deprecated in Java")
    override fun doInBackground(vararg p0: Void?): ArrayList<New> {
        try {
            //?b_start:int=20
            //val url = "https://www.saude.gov.br/fakenews?limitstart=0"
            //val url = "https://www.gov.br/saude/pt-br/assuntos/noticias/2022?$page"
            val url = "https://www.hurriyet.com.tr"
            val urlPage = "https://www.hurriyet.com.tr/teknoloji/?p=$page"
            val doc: Document = Jsoup.connect(urlPage).get()

            val base: Elements = doc.select("div.category__list__item")

            //get images inside of the div
            val div: Elements = base.select("a.category__list__item--cover")
            //get titles inside of the H2
            val tagHeading: Elements = base.select("h2")

            val size:Int = div.size-1
            for (index in 0..size){
                //get image link inside tag "img" with attribute src
                val imgUrl:String = div.select("img").eq(index).attr("data-src")
                //get text title inside tag "a"
                val title:String = tagHeading.select("a").eq(index).text()
                //get detail news link inside tag "a" with attribute "href
                val details:String = tagHeading.select("a").eq(index).attr("href")
               println("Result: I:$imgUrl T:$title D:$url$details")
                news.add(New(imgUrl,title,"$url$details"))
            }

        }catch (e: IOException) { e.printStackTrace()}
        return news
    }

    @Deprecated("Deprecated in Java")
    override fun onPostExecute(result: ArrayList<New>?) {
        loadedData.getWebData(result!!)
    }
}