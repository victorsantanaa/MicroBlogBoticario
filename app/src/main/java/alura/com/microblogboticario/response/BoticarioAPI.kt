package alura.com.microblogboticario.response

import retrofit2.http.GET

interface BoticarioAPI {

    @GET("data.json")
    suspend fun getNewsList(): New
}