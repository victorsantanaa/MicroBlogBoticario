package victor.com.microblogboticario.home.database

import victor.com.microblogboticario.home.model.PostModel
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [(PostModel::class)], version = 1)
abstract class PostDatabase : RoomDatabase() {
    abstract fun postDao(): PostsDao


}
