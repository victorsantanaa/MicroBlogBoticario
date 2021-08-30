package victor.com.microblogboticario.news.viewmodel

import victor.com.microblogboticario.news.model.NewsModel
import victor.com.microblogboticario.news.repository.NewsRepository
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