package victor.com.microblogboticario.home.repository

import victor.com.microblogboticario.home.model.PostModel
import victor.com.microblogboticario.home.view.returnListOfPostsFake
import victor.com.microblogboticario.MicroBlogApplication
import androidx.lifecycle.LiveData

class PostRepository {

    val dao = MicroBlogApplication.postDatabase!!.postDao()

    fun getAllPosts(): LiveData<List<PostModel>> {
        return dao.getAllPosts()
    }

    fun getPostById(postId: Long): LiveData<PostModel> {
        return dao.getPostById(postId)
    }

    fun putFakePostsOnDatabase() {
        Thread(Runnable {
            dao
                .insertAllPostsOnDatabase(returnListOfPostsFake())
        }).start()
    }

    fun putNewPostOnDatabase(post: PostModel) {
        Thread(Runnable {
            dao.insertNewPostOnDatabase(post)
        }).start()
    }

    fun deletePostFromDatabase(post: PostModel) {
        Thread(Runnable {
            dao.deleteByPostId(post.id)
        }).start()
    }

    fun updatePostOnDatabase(post: PostModel) {
        Thread(Runnable {
            dao.updatePost(post)
        }).start()
    }


    fun getAllByUserName(user: String?) {
        Thread(Runnable {
            dao.getPostsByUserName(user)
        }).start()
    }
}