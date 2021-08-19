package alura.com.microblogboticario.database

import alura.com.microblogboticario.model.News
import alura.com.microblogboticario.model.NewsModel
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [(News::class)], version = 1)
abstract class NewsDatabase: RoomDatabase() {

    abstract fun newsDao(): NewsDao

}