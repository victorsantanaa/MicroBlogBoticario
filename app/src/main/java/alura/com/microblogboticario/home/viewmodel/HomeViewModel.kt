package alura.com.microblogboticario.home.viewmodel

import alura.com.microblogboticario.home.model.PostModel
import alura.com.microblogboticario.home.repository.PostRepository
import alura.com.microblogboticario.news.model.NewsModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class HomeViewModel: ViewModel() {

    var postsRepository = PostRepository()

    fun getAllPostList() : LiveData<List<PostModel>> {
        return postsRepository.getAllPosts()
    }
    fun putFakeOnDatabase() {
        postsRepository.putFakePostsOnDatabase()
    }



}