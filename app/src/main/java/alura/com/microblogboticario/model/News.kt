package alura.com.microblogboticario.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "News")
data class News(

    var user_name: String,
    var user_picture: String,
    var message_content: String,
    var message_created: String,
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

)
