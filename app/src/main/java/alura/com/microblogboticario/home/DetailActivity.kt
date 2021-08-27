package alura.com.microblogboticario.home

import alura.com.microblogboticario.MainActivity
import alura.com.microblogboticario.R
import alura.com.microblogboticario.home.model.PostModel
import alura.com.microblogboticario.home.viewmodel.HomeViewModel
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import coil.compose.rememberImagePainter
import com.google.firebase.auth.FirebaseAuth

class DetailActivity : ComponentActivity() {

    private val auth = FirebaseAuth.getInstance()
    lateinit var homeViewModel: HomeViewModel

    private val post: PostModel by lazy {
        intent?.getSerializableExtra(POST_ID) as PostModel
    }

    companion object {

        private const val POST_ID = "post_id"

        fun newIntent(context: Context, post: PostModel) =
            Intent(context, DetailActivity::class.java).apply {
                putExtra(POST_ID, post)
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(application)
        )
            .get(HomeViewModel::class.java)


        setContent {
            DetailScreen(post = post, auth = auth, homeViewModel = homeViewModel, this)
        }
    }
}


@Composable
fun DetailScreen(post: PostModel, auth: FirebaseAuth, homeViewModel: HomeViewModel, context: Activity) {


    Column(
        Modifier
            .background(color = colorResource(id = R.color.white_gray))
            .fillMaxSize()
    ) {

        Card(
            border = BorderStroke(2.dp, Color.Transparent),
            elevation = 18.dp,
            backgroundColor = colorResource(id = R.color.white_gray),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .padding(6.dp)
                .fillMaxWidth()
                .align(CenterHorizontally)
        ) {
            Column(
                modifier = Modifier
                    .padding(6.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .wrapContentSize(Alignment.Center)

                ) {
                    Image(
                        painter = rememberImagePainter(
                            data = post.user_profile
                        ),
                        contentDescription = "Imagem do ${post.user_name}",
                        modifier = Modifier
                            .size(75.dp)
                            .clip(CircleShape)
                            .border(1.dp, Color.White, CircleShape)
                            .background(
                                color = colorResource(
                                    id = R.color.boticario_background
                                )
                            )
                    )
                    Spacer(Modifier.width(10.dp))
                    Text(post.user_name, style = MaterialTheme.typography.h5)

                }

                Spacer(Modifier.width(10.dp))
                Text(
                    post.message_content,
                    style = MaterialTheme.typography.subtitle1,
                    fontSize = 20.sp
                )

                Spacer(Modifier.width(10.dp))
                Text(
                    post.message_created,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.align(
                        End
                    )
                )
            }
        }
        if (auth.currentUser?.email == post.user_name) {
            Footer(post, homeViewModel, context)
        }
    }
}

@Composable
fun Footer(post: PostModel,homeViewModel: HomeViewModel, context: Activity) {
    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = End,
        modifier = Modifier.fillMaxWidth()
    ) {

    }
    Row(
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier.fillMaxWidth()
    ) {

        IconToggleButton(
            checked = false,
            onCheckedChange = {

            },
            modifier = Modifier.padding(8.dp),
            content = {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit")
            }
        )

        IconButton(
            onClick = {
                homeViewModel.deletePostFromDatabase(post)
                context.startActivity(Intent(context, MainActivity::class.java))
                context.finish()


            },
            modifier = Modifier.padding(8.dp),
            content = {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
            }
        )

    }
}



