package alura.com.microblogboticario.home.viewmodel

import alura.com.microblogboticario.home.model.PostModel
import alura.com.microblogboticario.home.repository.PostRepository
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class HomeViewModel: ViewModel() {

    private var postsRepository = PostRepository()

    val onResume = mutableStateOf("")
    val iDPostDetails: MutableState<Int> = mutableStateOf(0)
    val listOfPostDetail = mutableListOf<PostModel>()

    fun addToListDetailPost(post: PostModel) {
        listOfPostDetail.add(post)
    }

    fun onResumeToTrue() {
//        this.onResume.value = true
    }

    fun getAllPostList() : LiveData<List<PostModel>> {
        return postsRepository.getAllPosts()
    }
    fun putFakeOnDatabase() {
        postsRepository.putFakePostsOnDatabase()
    }

    fun deletePostFromDatabase(post: PostModel) {
        postsRepository.deletePostFromDatabase(post)
    }

    fun updatePostFromDatabase(post: PostModel) {
        postsRepository.updatePostOnDatabase(post)
    }

    fun getPostById(id: Int): LiveData<PostModel> {
        return postsRepository.getPostById(id.toLong())
    }

    fun getIdState() : Int {
        return iDPostDetails.value
    }

    fun updateIdState(id: Int) {
        iDPostDetails.value = id
    }
}