package alura.com.microblogboticario.ui.theme

import alura.com.microblogboticario.home.HomeScreen
import alura.com.microblogboticario.home.model.PostModel
import alura.com.microblogboticario.home.viewmodel.HomeViewModel
import alura.com.microblogboticario.newpost.viewmodel.NewPostViewModel
import alura.com.microblogboticario.news.activity.NewsViewModel
import alura.com.microblogboticario.ui.theme.ui.theme.NewPostScreen
import alura.com.microblogboticario.ui.theme.ui.theme.NewsScreen
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
            HomeScreen(list,auth, homeViewModel, navController, context)
        }
        composable(NavigationItem.News.route) {
            NewsScreen(newsViewModel)
        }
        composable(NavigationItem.NewPost.route) {
            NewPostScreen(navController, newPostViewModel)

        }
    }
}