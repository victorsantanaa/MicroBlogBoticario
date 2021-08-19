package alura.com.microblogboticario.repository

import alura.com.microblogboticario.MicroBlogApplication
import alura.com.microblogboticario.model.News
import alura.com.microblogboticario.model.ResponseModel
import alura.com.microblogboticario.service.Endpoint
import android.util.Log
import androidx.lifecycle.LiveData
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsRepository {

    val BASE_URL = ""
    val TAG = NewsRepository::class.java.simpleName

    fun getNews() : LiveData<List<News>> {
        return MicroBlogApplication.database!!.newsDao().getAllNews()
    }

    fun ApiCallAndPutInDB() {
        val gson = Gson()
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASE_URL)
            .build()

        val restApi = retrofit.create<Endpoint>(Endpoint::class.java)

        restApi.getNews().enqueue(object : Callback<ResponseModel?> {
            override fun onResponse(
                call: Call<ResponseModel?>,
                response: Response<ResponseModel?>
            ) {
                Log.e(TAG, response!!.body().toString())
                var list = mutableListOf<News>()
                val listNews = response.body()!!.news
                for (news in listNews) {
                    val user_name = news.user.name
                    val user_profile = news.user.profilePicture
                    val message_content = news.message.content
                    val message_created = news.message.createdAt

                    val news = News(user_name, user_profile, message_content, message_created)
                    list.add(news)
                }

                when(response.code()) {
                    200 ->
                        Thread {
                            MicroBlogApplication.database!!.newsDao().deleteAllNews()
                            MicroBlogApplication.database!!.newsDao().insertAllNews(list)
                        }.start()
                }
            }

            override fun onFailure(call: Call<ResponseModel?>, t: Throwable) {
                Log.e(TAG, "Algo deu errado!")
            }
        })
    }

}