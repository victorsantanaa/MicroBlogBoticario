package alura.com.microblogboticario.news

import alura.com.microblogboticario.news.database.NewsDatabase
import android.app.Application
import androidx.room.Room

class MicroBlogApplication: Application() {

    companion object {
        var database: NewsDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        database = Room
            .databaseBuilder(
                applicationContext,
                NewsDatabase::class.java,
                "news_db"
            )
            .fallbackToDestructiveMigration()
            .build()
    }

}