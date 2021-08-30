package alura.com.microblogboticario.news.viewmodel

import alura.com.microblogboticario.news.model.NewsModel
import alura.com.microblogboticario.news.repository.NewsRepository
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