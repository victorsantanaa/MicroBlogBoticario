package alura.com.microblogboticario.home.database

import alura.com.microblogboticario.home.model.PostModel
import alura.com.microblogboticario.news.model.NewsModel
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PostsDao {

    @Query("SELECT * FROM Posts")
    fun getAllPosts(): LiveData<List<PostModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewPostOnDatabase(post: PostModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPostsOnDatabase(newsList: List<PostModel>)

    @Query("DELETE FROM Posts")
    fun deleteAllPost()

    @Query("DELETE FROM Posts WHERE id = :postId")
    fun deleteByPostId(postId: Long)

    @Query("SELECT * FROM Posts where id = :postId")
    fun getPostById(postId: Long) : PostModel?

    @Update
    fun updatePost(post: PostModel)

}