package alura.com.microblogboticario

import alura.com.microblogboticario.model.News
import alura.com.microblogboticario.model.NewsModel
import alura.com.microblogboticario.model.ResponseModel
import alura.com.microblogboticario.service.Endpoint
import alura.com.microblogboticario.service.NetworkUtils
import alura.com.microblogboticario.ui.theme.ItemList
import alura.com.microblogboticario.ui.theme.ScrollingList
import alura.com.microblogboticario.ui.theme.ui.theme.MicroBlogBoticarioTheme
import alura.com.microblogboticario.ui.theme.ui.theme.NewsScreen
import alura.com.microblogboticario.viewmodel.NewsViewModel
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsActivity : ComponentActivity() {
    private val newsViewModel by viewModels<NewsViewModel>()
    lateinit var mNewsViewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mNewsViewModel = ViewModelProvider(this)
            .get(NewsViewModel::class.java)

        if (isNetworkConnected(this)) {
            mNewsViewModel.getAllNewsFromApi()
        } else {
            Toast.makeText(this, "Sem internet. Apresentando lista em cachê", Toast.LENGTH_SHORT).show()
        }
//        mNewsViewModel.getAllCountryList().observe(this, Observer<List<NewsModel>> {
//            newsList ->Log.e(NewsActivity::class.java.simpleName, newsList.toString())
//        })
        mNewsViewModel.getAllNewsFromDatabase().observe(this, Observer<List<News>> {
            newsList -> Log.e(NewsActivity::class.java.simpleName, newsList.toString())
            setContent(
                ItemList(image = new, message = , author = , created = , modifier = )
            )
        })
    }

    fun isNetworkConnected(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }
}
//        setContent {
//            val navController = rememberNavController()
//            MicroBlogBoticarioTheme {
//                NavHost(navController = navController, startDestination = "news_list") {
//                    composable("news_list") {
//                        NewsScreen(navController = navController, newsViewModel = newsViewModel)
//                    }
//                    composable("newsDetails") {
//
//                    }
//                }
//            }


//            var listNews: ArrayList<NewsModel>? = null
//            val retrofitClient = NetworkUtils
//                .getRetrofitInstance("https://gb-mobile-app-teste.s3.amazonaws.com/")
//            val endpoint = retrofitClient.create(Endpoint::class.java)
//            val callback = endpoint.getNews()

//            callback.enqueue(object : Callback<List<ResponseModel>?> {
//                override fun onResponse(
//                    call: Call<List<ResponseModel>?>,
//                    response: Response<List<ResponseModel>?>
//                ) {
//                    var responseBody = response.body()
//                    val news: List<NewsModel> = responseBody!![0].news
//                    setContent {
//                        ScrollingList(list = news)
//                    }
//                }
//
//                override fun onFailure(call: Call<List<ResponseModel>?>, t: Throwable) {
//                    Log.e("onFailure", "onFailure: " + t.message)
//                }
//            })
//            callback.enqueue(object : Callback<ResponseModel?> {
//                override fun onResponse(
//                    call: Call<ResponseModel?>,
//                    response: Response<ResponseModel?>
//                ) {
//                    var responseBody = response.body()!!.news
//                    response.body()
//                    setContent {
//                        ScrollingList(list = responseBody)
//                    }
//                }
//                override fun onFailure(call: Call<ResponseModel?>, t: Throwable) {
//                    Log.e("onFailure", "onFailure: " + t.message)
//                }
//            })

