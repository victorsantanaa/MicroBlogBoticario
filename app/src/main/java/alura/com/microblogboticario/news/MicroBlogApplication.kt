package alura.com.microblogboticario.news

import alura.com.microblogboticario.home.database.PostDatabase
import alura.com.microblogboticario.home.returnListOfPostsFake
import alura.com.microblogboticario.news.database.NewsDatabase
import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

class MicroBlogApplication: Application() {

    companion object {
        var newsDatabase: NewsDatabase? = null
        var postDatabase: PostDatabase? = null

    }

    override fun onCreate() {
        super.onCreate()
        newsDatabase = Room
            .databaseBuilder(
                applicationContext,
                NewsDatabase::class.java,
                "news_db"
            )
            .fallbackToDestructiveMigration()
            .build()

        postDatabase = Room
            .databaseBuilder(
                applicationContext,
                PostDatabase::class.java,
                "posts_db"
            )
            .fallbackToDestructiveMigration()
            .build()


    }

}