package alura.com.microblogboticario.viewmodel

import alura.com.microblogboticario.model.News
import alura.com.microblogboticario.model.NewsModel
import alura.com.microblogboticario.model.ResponseModel
import alura.com.microblogboticario.repository.NewsRepository
import alura.com.microblogboticario.service.Endpoint
import alura.com.microblogboticario.service.NewsService
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel() {

    private val api: Endpoint = NewsService.retrofitService
    var newsUser: List<NewsModel> by mutableStateOf(listOf())
    lateinit var response: Call<ResponseModel>
        private set
    lateinit var responseModel: ResponseModel
        private set

    lateinit var clickedNews: NewsModel
    lateinit var newsRepository: NewsRepository

    init {
        fetchResponse()
        newsRepository = NewsRepository()
    }

    fun getAllNewsFromDatabase(): LiveData<List<News>> {
        return newsRepository.getNews()
    }

    fun getAllNewsFromApi() {
        newsRepository.ApiCallAndPutInDB()
    }


    private fun fetchResponse() {
        viewModelScope.launch {
            response = api.getNews()
            response.enqueue(object : Callback<ResponseModel?> {
                override fun onResponse(
                    call: Call<ResponseModel?>,
                    response: Response<ResponseModel?>
                ) {
                    responseModel = response.body()!!
                }

                override fun onFailure(call: Call<ResponseModel?>, t: Throwable) {
                    println(t.message)
                }
            })
        }
    }

    fun onItemClicked(item: NewsModel) {
        clickedNews = item
//        fetchStoryComments()
    }

}