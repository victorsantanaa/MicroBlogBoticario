package alura.com.microblogboticario

import alura.com.microblogboticario.model.NewsModel
import alura.com.microblogboticario.model.ResponseModel
import alura.com.microblogboticario.service.Endpoint
import alura.com.microblogboticario.service.NetworkUtils
import alura.com.microblogboticario.ui.theme.*
import alura.com.microblogboticario.viewmodel.NewsViewModel
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.StringBuilder

class MainActivity : ComponentActivity() {
    private val newsViewModel by viewModels<NewsViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        val context = this
        val auth = FirebaseAuth.getInstance()
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen(auth , newsViewModel)
        }
    }
}

@Composable
fun MainScreen(auth: FirebaseAuth, newsViewModel: NewsViewModel ) {
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