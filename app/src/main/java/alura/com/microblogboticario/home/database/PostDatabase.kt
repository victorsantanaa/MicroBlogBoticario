package alura.com.microblogboticario.home.database

import alura.com.microblogboticario.home.model.PostModel
import alura.com.microblogboticario.news.database.NewsDao
import alura.com.microblogboticario.news.model.NewsModel
import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [(PostModel::class)], version = 1)
abstract class PostDatabase : RoomDatabase() {
    abstract fun postDao(): PostsDao


}
