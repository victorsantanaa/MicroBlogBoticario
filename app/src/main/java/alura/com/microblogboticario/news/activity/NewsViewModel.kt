package alura.com.microblogboticario.news.activity

import alura.com.microblogboticario.news.model.NewsModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class NewsViewModel: ViewModel() {
    var newsRepository: NewsRepository = NewsRepository()



    fun getAllNewsList() : LiveData<List<NewsModel>> {
        return newsRepository.getAllNews()
    }

    fun getNewsFromApiAndPutInDatabase() {
        newsRepository.apiCallAndPutInDatabase()
    }

}