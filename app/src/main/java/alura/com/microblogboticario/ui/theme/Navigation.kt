package alura.com.microblogboticario.ui.theme

import alura.com.microblogboticario.news.activity.NewsViewModel
import alura.com.microblogboticario.ui.theme.ui.theme.HomeScreen
import alura.com.microblogboticario.ui.theme.ui.theme.NewPostScreen
import alura.com.microblogboticario.ui.theme.ui.theme.NewsScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.firebase.auth.FirebaseAuth

@Composable
fun NavigationBottom(navController: NavHostController, auth: FirebaseAuth, newsViewModel: NewsViewModel) {
    NavHost(navController, startDestination = NavigationItem.Home.route) {
        composable(NavigationItem.Home.route) {
            HomeScreen(auth)
        }
        composable(NavigationItem.News.route) {
            NewsScreen(newsViewModel)
        }
        composable(NavigationItem.NewPost.route) {
            NewPostScreen()
        }
    }
}