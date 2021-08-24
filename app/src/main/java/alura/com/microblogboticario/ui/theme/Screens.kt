package alura.com.microblogboticario.ui.theme.ui.theme

import alura.com.microblogboticario.LoginActivity
import alura.com.microblogboticario.R
import alura.com.microblogboticario.news.activity.NewsViewModel
import alura.com.microblogboticario.news.model.NewsModel
import alura.com.microblogboticario.ui.theme.ScrollingList
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
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
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Observer
import com.google.firebase.auth.FirebaseAuth

@Composable
fun HomeScreen(auth: FirebaseAuth) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Home View",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
        ButtonLogout(auth)
    }
}

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

    newsViewModel.getAllNewsList().observe(owner, Observer<List<NewsModel>>  {
        newsList ->
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
        ScrollingList(list)
        }


    }


@Preview(showBackground = true)
@Composable
fun NewsScreenPreview() {
//    NewsScreen()
}

@Composable
fun NewPostScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.colorPrimary))
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Nova Postagem View",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MoviesScreenPreview() {
    NewPostScreen()
}