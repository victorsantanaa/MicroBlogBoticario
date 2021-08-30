package victor.com.microblogboticario

import victor.com.microblogboticario.home.database.PostDatabase
import victor.com.microblogboticario.news.database.NewsDatabase
import android.app.Application
import androidx.room.Room

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