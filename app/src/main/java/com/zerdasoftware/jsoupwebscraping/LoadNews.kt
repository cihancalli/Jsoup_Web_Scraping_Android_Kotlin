@file:Suppress("DEPRECATION")

package com.zerdasoftware.jsoupwebscraping

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.IOException

class LoadNews (activity: AppCompatActivity?, var page:String) : AsyncTask<Void, Void, ArrayList<New>>() {

    private var news:ArrayList<New> = ArrayList()
    private var loadedData = activity as IJsoupData

    @Deprecated("Deprecated in Java")
    override fun doInBackground(vararg p0: Void?): ArrayList<New> {
        try {
            //?b_start:int=20
            //val url = "https://www.saude.gov.br/fakenews?limitstart=0"
            val url = "https://www.gov.br/saude/pt-br/assuntos/noticias/2022?$page"
            val doc: Document = Jsoup.connect(url).get()

            //get images inside of the div
            val div: Elements = doc.select("div.imagem")
            //get titles inside of the H2
            val tagHeading: Elements = doc.select("h2.titulo")

            val size:Int = div.size
            for (index in 0..size){
                //get image link inside tag "img" with attribute src
                val imgUrl:String = div.select("img").eq(index).attr("src")
                //get text title inside tag "a"
                val title:String = tagHeading.select("a").eq(index).text()
                //get detail news link inside tag "a" with attribute "href
                val details:String = tagHeading.select("a").attr("href")
                println("Result: I:$imgUrl T:$title D:$details")
                news.add(New(imgUrl,title,details))
            }

        }catch (e: IOException) { e.printStackTrace()}
        return news
    }

    @Deprecated("Deprecated in Java")
    override fun onPostExecute(result: ArrayList<New>?) {
        loadedData.getWebData(result!!)
    }
}