package alura.com.microblogboticario.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseModel(
    @SerializedName("news")
    @Expose
    var news : List<NewsModel>,
)
