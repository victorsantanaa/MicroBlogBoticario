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


@Composable
fun NavigationSplash() {
    val navController: NavHostController = rememberNavController()
    NavHost(navController, startDestination = "splash_screen") {
        composable("splash_screen") {
            SplashScreen(navController = navController)
        }
        composable("main_screen") {
            MainScreen()
        }
    }
}

@Composable
fun NavigationBottom(navController: NavHostController) {
    NavHost(navController, startDestination = NavigationItem.Home.route) {
        composable(NavigationItem.Home.route) {
            HomeScreen()
        }
        composable(NavigationItem.News.route) {
            NewsScreen()
        }
        composable(NavigationItem.NewPost.route) {
            NewPostScreen()
        }
    }
}