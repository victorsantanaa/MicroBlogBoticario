package alura.com.microblogboticario

import alura.com.microblogboticario.home.viewmodel.HomeViewModel
import alura.com.microblogboticario.newpost.viewmodel.NewPostViewModel
import alura.com.microblogboticario.news.activity.NewsViewModel
import alura.com.microblogboticario.news.model.NewsModel
import alura.com.microblogboticario.ui.theme.BottomNavigationBar
import alura.com.microblogboticario.ui.theme.NavigationBottom
import alura.com.microblogboticario.ui.theme.TopBar
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {

    lateinit var newsViewModel: NewsViewModel
    lateinit var newPostViewModel: NewPostViewModel
    lateinit var homeViewModel: HomeViewModel

    val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val auth = FirebaseAuth.getInstance()
        homeViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(application)
        )
            .get(HomeViewModel::class.java)

        newsViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(application)
        )
            .get(NewsViewModel::class.java)
        newPostViewModel =
            ViewModelProvider(
                this, ViewModelProvider.AndroidViewModelFactory(application)
            )
                .get(
                    NewPostViewModel::class.java
                )

        homeViewModel.putFakeOnDatabase()
        newsViewModel.getNewsFromApiAndPutInDatabase()


        setContent {
            MainScreen(auth, newsViewModel, newPostViewModel, homeViewModel)
        }
    }
}

@Composable
fun MainScreen(
    auth: FirebaseAuth,
    newsViewModel: NewsViewModel,
    newPostViewModel: NewPostViewModel,
    homeViewModel: HomeViewModel
) {
    val navController: NavHostController = rememberNavController()
    Scaffold(
        topBar = { TopBar() },
        bottomBar = { BottomNavigationBar(navController) }
    ) {
        NavigationBottom(navController, auth, newsViewModel, newPostViewModel, homeViewModel)
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
//    MainScreen()
}


@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    TopBar()
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar(rememberNavController())
}