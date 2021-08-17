package alura.com.microblogboticario.ui.theme.ui.theme

import alura.com.microblogboticario.LoginActivity
import alura.com.microblogboticario.MainActivity
import alura.com.microblogboticario.R
import alura.com.microblogboticario.model.NewsModel
import alura.com.microblogboticario.service.Endpoint
import alura.com.microblogboticario.service.NetworkUtils
import alura.com.microblogboticario.ui.theme.ScrollingList
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun HomeScreen(auth: FirebaseAuth) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.colorPrimaryDark))
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
fun NewsScreen() {
    val context = LocalContext.current
    var listNews: List<NewsModel>? = null
    val retrofitClient = NetworkUtils
        .getRetrofitInstance("https://gb-mobile-app-teste.s3.amazonaws.com/data.json")
    val endpoint = retrofitClient.create(Endpoint::class.java)
    val callback = endpoint.getNews()

    callback.enqueue(object : Callback<List<NewsModel>> {
        override fun onResponse(
            call: Call<List<NewsModel>>,
            response: Response<List<NewsModel>>
        ) {
            if(response.body() != null) {
                listNews = response.body()!!
            } else {
                Toast.makeText(context,
                    response.errorBody().toString(),
                    Toast.LENGTH_SHORT).show()
            }
        }

        override fun onFailure(call: Call<List<NewsModel>>, t: Throwable) {
            Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
        }

    })


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.colorPrimaryDark))
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Notícias Boticário View",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )

        }

    listNews?.let { ScrollingList(it) }
    }


@Preview(showBackground = true)
@Composable
fun NewsScreenPreview() {
    NewsScreen()
}

@Composable
fun NewPostScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.colorPrimaryDark))
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