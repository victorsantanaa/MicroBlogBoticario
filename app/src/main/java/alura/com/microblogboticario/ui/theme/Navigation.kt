package alura.com.microblogboticario.ui.theme

import alura.com.microblogboticario.MainScreen
import alura.com.microblogboticario.ui.theme.ui.theme.HomeScreen
import alura.com.microblogboticario.ui.theme.ui.theme.NewPostScreen
import alura.com.microblogboticario.ui.theme.ui.theme.NewsScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth


@Composable
fun NavigationSplash() {
    val navController: NavHostController = rememberNavController()
    NavHost(navController, startDestination = "splash_screen") {
        composable("splash_screen") {
            SplashScreen(navController = navController)
        }
        composable("main_screen") {

        }
    }
}

@Composable
fun NavigationBottom(navController: NavHostController, auth: FirebaseAuth) {
    NavHost(navController, startDestination = NavigationItem.Home.route) {
        composable(NavigationItem.Home.route) {
            HomeScreen(auth)
        }
        composable(NavigationItem.News.route) {
            NewsScreen()
        }
        composable(NavigationItem.NewPost.route) {
            NewPostScreen()
        }
    }
}