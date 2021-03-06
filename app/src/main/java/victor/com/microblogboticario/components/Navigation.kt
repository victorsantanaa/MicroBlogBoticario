package victor.com.microblogboticario.components

import victor.com.microblogboticario.home.view.HomeScreen
import victor.com.microblogboticario.home.model.PostModel
import victor.com.microblogboticario.home.viewmodel.HomeViewModel
import victor.com.microblogboticario.home.viewmodel.NewPostViewModel
import victor.com.microblogboticario.news.viewmodel.NewsViewModel
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.firebase.auth.FirebaseAuth

@ExperimentalComposeUiApi
@Composable
fun NavigationBottom(
    navController: NavHostController,
    auth: FirebaseAuth,
    newsViewModel: NewsViewModel,
    newPostViewModel: NewPostViewModel,
    homeViewModel: HomeViewModel,
    context: Context,
    list: MutableList<PostModel>
) {
    NavHost(navController, startDestination = NavigationItem.Home.route) {
        composable(NavigationItem.Home.route) {
            HomeScreen(list,auth, homeViewModel, context)
        }
        composable(NavigationItem.News.route) {
            NewsScreen(newsViewModel)
        }
        composable(NavigationItem.NewPost.route) {
            NewPostScreen(navController, newPostViewModel)

        }
    }
}