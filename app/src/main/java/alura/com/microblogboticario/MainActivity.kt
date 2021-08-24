package alura.com.microblogboticario

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
    val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val auth = FirebaseAuth.getInstance()
        newsViewModel = ViewModelProvider(this,
                        ViewModelProvider.AndroidViewModelFactory(application))
                        .get(NewsViewModel::class.java)

        newsViewModel.getNewsFromApiAndPutInDatabase()

        setContent {
            MainScreen(auth, newsViewModel)
        }
    }
}

@Composable
fun MainScreen(auth: FirebaseAuth, newsViewModel: NewsViewModel) {
    val navController: NavHostController = rememberNavController()
    Scaffold(
        topBar = { TopBar() },
        bottomBar = { BottomNavigationBar(navController) }
    ) {
        NavigationBottom(navController, auth, newsViewModel)
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