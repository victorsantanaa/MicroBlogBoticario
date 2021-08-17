package alura.com.microblogboticario.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("name")
    @Expose
    var name : String,
    @SerializedName("profile_picture")
    @Expose
    val profilePicture: String
)
