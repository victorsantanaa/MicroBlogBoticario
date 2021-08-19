package alura.com.microblogboticario

import alura.com.microblogboticario.database.NewsDatabase
import android.app.Application
import androidx.room.Room

class MicroBlogApplication : Application() {

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