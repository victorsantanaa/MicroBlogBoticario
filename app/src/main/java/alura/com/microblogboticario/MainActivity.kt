package alura.com.microblogboticario

import alura.com.microblogboticario.ui.theme.*
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val auth = FirebaseAuth.getInstance()
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen(auth)
        }
    }
}

@Composable
fun MainScreen(auth: FirebaseAuth) {
    val navController: NavHostController = rememberNavController()
    Scaffold(
        topBar = { TopBar() },
        bottomBar = { BottomNavigationBar(navController) }
    ) {
        NavigationBottom(navController, auth)
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