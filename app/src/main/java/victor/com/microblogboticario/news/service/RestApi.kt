package victor.com.microblogboticario.news.service

import victor.com.microblogboticario.news.model.Response
import retrofit2.Call
import retrofit2.http.GET

interface RestApi {

    @GET("data.json")
    fun getResponse(): Call<Response>

}