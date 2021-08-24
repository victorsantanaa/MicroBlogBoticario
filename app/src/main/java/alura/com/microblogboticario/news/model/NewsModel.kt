package alura.com.microblogboticario.news.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "News")
data class NewsModel(

    var user_name: String,
    var user_profile: String,
    var message_content: String,
    var message_created: String,
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

)