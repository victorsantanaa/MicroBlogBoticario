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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    val navController: NavHostController = rememberNavController()
    Scaffold(
        topBar = { TopBar() },
        bottomBar = { BottomNavigationBar(navController) }
    ) {
        NavigationBottom(navController)
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
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