package alura.com.microblogboticario.service

import alura.com.microblogboticario.model.NewsModel
import retrofit2.Call
import retrofit2.http.GET

interface Endpoint {

    @GET("news")
    fun getNews() : Call<List<NewsModel>>
}