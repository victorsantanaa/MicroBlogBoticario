package victor.com.microblogboticario.components

import victor.com.microblogboticario.R
import victor.com.microblogboticario.home.viewmodel.NewPostViewModel
import victor.com.microblogboticario.news.model.NewsModel
import victor.com.microblogboticario.news.viewmodel.NewsViewModel
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth


@Composable
fun NewsScreen(newsViewModel: NewsViewModel) {
    val owner = LocalLifecycleOwner.current
    val listNews: MutableList<NewsModel> = mutableListOf()
    val list by remember {
        mutableStateOf(listNews)
    }

    newsViewModel.getAllNewsList().observe(owner, { newsList ->
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
    val user = FirebaseAuth.getInstance().currentUser
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white_gray))
            .wrapContentSize(Alignment.TopCenter)
    ) {

        Spacer(Modifier.height(16.dp))
        Text(
            text = "Digite uma postagem nova, " + user?.displayName.toString(),
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
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
            val textState = newPostViewModel.newPostText.value
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