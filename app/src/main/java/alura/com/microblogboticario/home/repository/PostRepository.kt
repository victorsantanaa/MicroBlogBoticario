package alura.com.microblogboticario.home.repository

import alura.com.microblogboticario.home.model.PostModel
import alura.com.microblogboticario.home.returnListOfPostsFake
import alura.com.microblogboticario.news.MicroBlogApplication
import androidx.lifecycle.LiveData

class PostRepository {

    fun getAllPosts(): LiveData<List<PostModel>> {
        return MicroBlogApplication.postDatabase!!.postDao().getAllPosts()
    }

    fun putFakePostsOnDatabase() {
        Thread(Runnable {
            MicroBlogApplication.postDatabase!!.postDao()
                .insertAllPostsOnDatabase(returnListOfPostsFake())
        }).start()
    }

    fun putNewPostOnDatabase(post: PostModel) {
        Thread(Runnable {
            MicroBlogApplication.postDatabase!!.postDao().insertNewPostOnDatabase(post)
        }).start()
    }
}