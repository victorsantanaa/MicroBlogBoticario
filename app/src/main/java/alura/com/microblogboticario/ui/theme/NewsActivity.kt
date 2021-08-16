package alura.com.microblogboticario.ui.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import alura.com.microblogboticario.ui.theme.ui.theme.MicroBlogBoticarioTheme

class NewsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MicroBlogBoticarioTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting2("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting2(name: String) {
    Text(text = "News $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    MicroBlogBoticarioTheme {
        Greeting2("Activity")
    }
}