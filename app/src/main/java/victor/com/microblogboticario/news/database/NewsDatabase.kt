package victor.com.microblogboticario.news.database

import victor.com.microblogboticario.news.model.NewsModel
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [(NewsModel::class)], version = 1)
abstract class NewsDatabase: RoomDatabase() {

    abstract fun newsDao(): NewsDao

}