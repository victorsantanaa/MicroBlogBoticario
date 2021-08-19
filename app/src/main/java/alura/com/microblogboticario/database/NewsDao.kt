package alura.com.microblogboticario.database

import alura.com.microblogboticario.model.News
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NewsDao {

    @Query("SELECT * FROM News")
    fun getAllNews(): LiveData<List<News>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllNews(newsList: List<News>)

    @Query("DELETE FROM News")
    fun deleteAllNews()

}