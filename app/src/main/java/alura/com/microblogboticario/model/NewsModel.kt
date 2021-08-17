package alura.com.microblogboticario.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NewsModel(
    @SerializedName("user")
    @Expose
    var user : UserModel,
    @SerializedName("message")
    @Expose
    val message: MessageModel

)
