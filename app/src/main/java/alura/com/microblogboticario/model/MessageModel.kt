package alura.com.microblogboticario.model

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class MessageModel(
    @SerializedName("content")
    @Expose
    var content : String,
    @SerializedName("created_at")
    @Expose
    val createdAt: String
)

