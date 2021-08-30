package alura.com.microblogboticario

import alura.com.microblogboticario.home.model.PostModel
import alura.com.microblogboticario.home.viewmodel.HomeViewModel
import alura.com.microblogboticario.home.viewmodel.NewPostViewModel
import alura.com.microblogboticario.news.viewmodel.NewsViewModel
import alura.com.microblogboticario.components.BottomNavigationBar
import alura.com.microblogboticario.components.NavigationBottom
import alura.com.microblogboticario.components.TopBar
import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import java.time.Instant
import java.time.format.DateTimeFormatter

class MainActivity : ComponentActivity() {
    private val exampleLiveData = MutableLiveData("")

    private lateinit var newsViewModel: NewsViewModel
    private lateinit var newPostViewModel: NewPostViewModel
    private lateinit var homeViewModel: HomeViewModel

    private lateinit var context: Activity

    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val auth = FirebaseAuth.getInstance()

        context = this

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

        val listPost: MutableList<PostModel> = mutableListOf()
        val list = mutableStateOf(listPost)

        homeViewModel.putFakeOnDatabase()
        homeViewModel.getAllPostList().observe(this, { newList ->
            listPost.addAll(newList)
        })


        setContent {
            MainScreen(auth, newsViewModel, newPostViewModel, homeViewModel, context, list.value)
        }
    }

    override fun onResume() {
        super.onResume()
        exampleLiveData.value = DateTimeFormatter.ISO_INSTANT.format(Instant.now())
    }

}

@ExperimentalComposeUiApi
@Composable
fun MainScreen(
    auth: FirebaseAuth,
    newsViewModel: NewsViewModel,
    newPostViewModel: NewPostViewModel,
    homeViewModel: HomeViewModel,
    context: Activity,
    list: MutableList<PostModel>
) {
    val navController: NavHostController = rememberNavController()
    Scaffold(
        topBar = { TopBar(context) },
        bottomBar = { BottomNavigationBar(navController) }
    ) {
        NavigationBottom(navController, auth, newsViewModel, newPostViewModel, homeViewModel, context, list)
    }
}