package victor.com.microblogboticario.home.viewmodel

import victor.com.microblogboticario.home.repository.PostRepository
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class ProfileViewModel : ViewModel() {

    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    var postsRepository = PostRepository()

    fun getPostsForProfile() {
        postsRepository.getAllByUserName(auth.currentUser?.email)
    }

}