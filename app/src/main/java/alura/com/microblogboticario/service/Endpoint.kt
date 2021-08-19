package alura.com.microblogboticario.service

import alura.com.microblogboticario.model.ResponseModel
import retrofit2.Call
import retrofit2.http.GET

interface Endpoint {

    @GET("data.json")
    fun getNews() : Call<ResponseModel>
}