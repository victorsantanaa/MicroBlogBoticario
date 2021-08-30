package alura.com.microblogboticario.home.viewmodel

import alura.com.microblogboticario.utils.Utils
import alura.com.microblogboticario.home.model.PostModel
import alura.com.microblogboticario.home.repository.PostRepository
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import java.time.LocalDateTime

class NewPostViewModel() : ViewModel() {
    //var newsRepository: NewsRepository = NewsRepository()
    var postRepository = PostRepository()
    val auth: FirebaseAuth = FirebaseAuth.getInstance()

    val newPostText = mutableStateOf(" ")
    val textToPublish = mutableStateOf(" ")

    fun onNewPostTextChanged(text: String) {
        this.newPostText.value = text
    }

    fun onPublishClick(text: String) {
        this.textToPublish.value = text
        var date = Utils.formatDate(LocalDateTime.now().toString())
        val user = auth.currentUser?.displayName
        val picture =
            "https://thumbs.dreamstime.com/b/default-avatar-profile-icon-social-media-user-vector-default-avatar-profile-icon-social-media-user-vector-portrait-176194876.jpg"
        postRepository.putNewPostOnDatabase(
            PostModel(user.toString(), picture, text, date)
        )
    }

}