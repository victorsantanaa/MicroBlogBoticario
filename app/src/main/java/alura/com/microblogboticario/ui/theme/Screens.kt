package alura.com.microblogboticario.ui.theme.ui.theme

import alura.com.microblogboticario.LoginActivity
import alura.com.microblogboticario.R
import alura.com.microblogboticario.newpost.viewmodel.NewPostViewModel
import alura.com.microblogboticario.news.activity.NewsViewModel
import alura.com.microblogboticario.news.model.NewsModel
import alura.com.microblogboticario.ui.theme.NavigationItem
import alura.com.microblogboticario.ui.theme.ScrollingListNews
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Observer
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth


@Composable
fun ButtonLogout(auth: FirebaseAuth) {
    val context = LocalContext.current

    Button(onClick = {
        Toast.makeText(context, "Saindo", Toast.LENGTH_SHORT).show()
        auth.signOut()
        context.startActivity(Intent(context, LoginActivity::class.java))

    }) {
        Text(text = "Sair da conta")

    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
//    HomeScreen()
}

@Composable
fun NewsScreen(newsViewModel: NewsViewModel) {
    val context = LocalContext.current
    val owner = LocalLifecycleOwner.current
    var listNews: MutableList<NewsModel> = mutableListOf()
    var list by remember {
        mutableStateOf(listNews)
    }

    newsViewModel.getAllNewsList().observe(owner, Observer<List<NewsModel>> { newsList ->
        listNews.addAll(newsList)
        Log.e("NewsScreen: ", listNews.toString())
    })


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.93f)
            .background(colorResource(id = R.color.white))
            .wrapContentSize(Alignment.Center)
    ) {
        ScrollingListNews(list)
    }


}

@Composable
fun NewPostScreen(
    navController: NavHostController,
    newPostViewModel: NewPostViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white_gray))
            .wrapContentSize(Alignment.TopCenter)
    ) {
        Text(
            text = "Nova Postagem View",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
        Card(
            border = BorderStroke(2.dp, Color.Transparent),
            elevation = 12.dp,
            backgroundColor = colorResource(id = R.color.white_gray),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .padding(10.dp)
                .height(200.dp)
                .fillMaxWidth()
        ) {
            var textState = newPostViewModel.newPostText.value
            val maxChar = 280
            TextField(
                value = textState,
                onValueChange = { newPostViewModel.onNewPostTextChanged(it.take(maxChar))},
                label = { Text("No que você está pensando?") }
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        Button(
            onClick = {
                newPostViewModel.onPublishClick(newPostViewModel.newPostText.value)
                newPostViewModel.onNewPostTextChanged("")
                navController.navigate(NavigationItem.Home.route)
            },
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Black,
                contentColor = Color.White
            ),
            modifier = Modifier
                .align(Alignment.End)
                .padding(10.dp)
                .width(110.dp)
                .height(50.dp)
        ) {
            Text(text = "Publicar", fontWeight = FontWeight.Bold)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MoviesScreenPreview() {
//    NewPostScreen()
}