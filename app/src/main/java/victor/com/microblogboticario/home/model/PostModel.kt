package victor.com.microblogboticario.home.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Posts")
data class PostModel(
    var user_name: String,
    var user_profile: String,
    var message_content: String,
    var message_created: String,
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
) : Serializable


