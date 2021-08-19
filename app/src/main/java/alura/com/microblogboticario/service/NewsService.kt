package alura.com.microblogboticario.service

import alura.com.microblogboticario.model.NewsModel
import alura.com.microblogboticario.model.ResponseModel
import alura.com.microblogboticario.ui.theme.ScrollingList
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//var listNews: ArrayList<NewsModel>? = null
val retrofitClient = NetworkUtils
    .getRetrofitInstance("https://gb-mobile-app-teste.s3.amazonaws.com/")
//val endpoint = retrofitClient.create(Endpoint::class.java)
//val callback = endpoint.getNews()

object NewsService {
    val retrofitService: Endpoint by lazy {
        retrofitClient.create(Endpoint::class.java)
    }
}


//val x = callback.enqueue(object : Callback<ResponseModel?> {
//    override fun onResponse(
//        call: Call<ResponseModel?>,
//        response: Response<ResponseModel?>
//    ) {
//        var responseBody = response.body()!!.news
//        response.body()
//        ScrollingList(list = responseBody)
//
//    }
//    override fun onFailure(call: Call<ResponseModel?>, t: Throwable) {
//        Log.e("onFailure", "onFailure: " + t.message)
//    }
//})