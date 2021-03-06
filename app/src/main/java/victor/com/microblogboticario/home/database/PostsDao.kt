package victor.com.microblogboticario.home.database

import victor.com.microblogboticario.home.model.PostModel
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
    fun getPostById(postId: Long) : LiveData<PostModel>

    @Update
    fun updatePost(post: PostModel)

    @Query("SELECT * FROM Posts WHERE user_name = :user")
    fun getPostsByUserName(user: String?) : LiveData<List<PostModel>>

}