package alura.com.microblogboticario.news.activity

import alura.com.microblogboticario.news.MicroBlogApplication
import alura.com.microblogboticario.news.model.New
import alura.com.microblogboticario.news.model.NewsModel
import alura.com.microblogboticario.news.model.Response
import alura.com.microblogboticario.news.service.RestApi
import android.util.Log
import androidx.lifecycle.LiveData
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsRepository {

    val BASE_URL = "https://gb-mobile-app-teste.s3.amazonaws.com/"
    val TAG = NewsRepository::class.java.simpleName

    fun getAllNews() : LiveData<List<NewsModel>> {
        return MicroBlogApplication.database!!.newsDao().getAllNews()
    }

    fun apiCallAndPutInDatabase() {
        val gson = Gson()
        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL)
                .build()

        val restApi = retrofit.create<RestApi>(RestApi::class.java)

        restApi.getResponse().enqueue(object : Callback<Response?> {
            override fun onResponse(
                call: Call<Response?>,
                response: retrofit2.Response<Response?>
            ) {
                Log.e(TAG,response!!.body().toString())
                when(response.code()) {
                    200-> {
                        Thread(Runnable {
                            val listNewsResponse : List<New> = response.body()!!.news
                            val listNewsResult : List <NewsModel> = formatListNewsToDatabase(listNewsResponse)

                            MicroBlogApplication.database!!.newsDao().deleAllNews()
                            MicroBlogApplication.database!!.newsDao().insertAllNewsOnDatabase(listNewsResult)
                        }).start()
                    }
                }
            }

            override fun onFailure(call: Call<Response?>, t: Throwable) {
                Log.e(TAG,"OOPS!! something went wrong..")
            }
        })
    }

    fun formatListNewsToDatabase(listNews: List<New>) : List<NewsModel>{

        var resultList : MutableList<NewsModel> = mutableListOf()
        for(new in listNews) {
            val user_name = new.user.name
            val user_picture = new.user.profile_picture
            val message_content = new.message.content
            val message_created = new.message.created_at
            val newsModel = NewsModel(user_name, user_picture, message_content, message_created)
            resultList.add(newsModel)
        }

        return resultList
    }

}
