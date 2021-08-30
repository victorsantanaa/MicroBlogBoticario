package victor.com.microblogboticario.news.database

import victor.com.microblogboticario.news.model.NewsModel
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NewsDao {

    @Query("SELECT * FROM News")
    fun getAllNews(): LiveData<List<NewsModel>>

    @Query("SELECT * FROM News")
    fun getNews(): List<NewsModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllNewsOnDatabase(newsList: List<NewsModel>)

    @Query("DELETE FROM News")
    fun deleAllNews()

}