package victor.com.microblogboticario.home.view

import victor.com.microblogboticario.MainActivity
import victor.com.microblogboticario.R
import victor.com.microblogboticario.utils.Utils
import victor.com.microblogboticario.home.model.PostModel
import victor.com.microblogboticario.home.viewmodel.HomeViewModel
import victor.com.microblogboticario.components.DetailTopBar
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
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import coil.compose.rememberImagePainter
import com.google.firebase.auth.FirebaseAuth
import java.time.LocalDateTime

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
            Scaffold(topBar = { DetailTopBar(activity = this) }) {
                DetailScreen(post = post, auth = auth, homeViewModel = homeViewModel, this)
            }

        }
    }
}


@Composable
fun DetailScreen(
    post: PostModel,
    auth: FirebaseAuth,
    homeViewModel: HomeViewModel,
    context: Activity
) {

    val isEditMode = remember {
        mutableStateOf(false)
    }

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

                Spacer(Modifier.height(10.dp))

                if (isEditMode.value) {
                    homeViewModel.onPostTextChanged(post.message_content)
                    val textState: String = homeViewModel.postTextEdit.value
                    val maxChar = 280

                    val query = remember {
                        mutableStateOf(textState)
                    }
                    OutlinedTextField(
                        value = query.value,
                        onValueChange = {
                            query.value = it.take(maxChar)
                            homeViewModel.onPostTextChanged(it.take(maxChar))
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Transparent)
                    )

                    Spacer(Modifier.height(10.dp))

                    Button(
                        onClick = {
                            post.message_content = query.value
                            post.message_created =
                                "editado em " + Utils.formatDate(LocalDateTime.now().toString())
                            homeViewModel.updatePostFromDatabase(post)
                            goToMainActivity(context)
                            context.finish()
                        },
                        shape = RoundedCornerShape(30.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Black,
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .align(Alignment.End)
                            .padding(8.dp)
                            .width(90.dp)
                            .height(35.dp)
                    ) {
                        Text(text = "Editar", fontWeight = FontWeight.Bold)
                    }
                } else {
                    SelectionContainer {
                        Column {
                            Text(
                                post.message_content,
                                style = MaterialTheme.typography.subtitle1,
                                fontSize = 20.sp
                            )
                        }
                    }
                }


                Spacer(Modifier.height(10.dp))

                Text(
                    post.message_created,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.align(
                        End
                    )
                )
            }
        }
        if (auth.currentUser?.displayName == post.user_name) {
            Footer(post, homeViewModel, context, isEditMode)
        }
    }
}

@Composable
fun Footer(
    post: PostModel,
    homeViewModel: HomeViewModel,
    context: Activity,
    isEditMode: MutableState<Boolean>
) {

    val openDialog = remember {
        mutableStateOf(false)
    }

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
                isEditMode.value = !isEditMode.value
            },
            modifier = Modifier.padding(8.dp),
            content = {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit")
            }
        )

        IconButton(
            onClick = {
                openDialog.value = true
                //deleteFromDatabase(homeViewModel, post, context)


            },
            modifier = Modifier.padding(8.dp),
            content = {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
            }
        )

    }

    if (openDialog.value) {
        AlertDialog(
            title = {
                Text("Apagar postagem!")
            },
            text = { Text("Você deseja realmente deletar a postagem?") },
            onDismissRequest = { openDialog.value = false },
            modifier = Modifier
                .fillMaxWidth()
                .border(BorderStroke(2.dp, Color.Transparent))
                .shadow(12.dp),
            confirmButton = {
                TextButton(onClick = {
                    deleteFromDatabase(homeViewModel, post, context)
                    openDialog.value = false
                }) {
                    Text(text = "Apagar", color = Color.Black)
                }
            },
            dismissButton = {
                TextButton(onClick = { openDialog.value = false }) {
                    Text(text = "Cancelar", color = Color.Black)
                }
            }
        )

    }
}

private fun deleteFromDatabase(
    homeViewModel: HomeViewModel,
    post: PostModel,
    context: Activity
) {
    homeViewModel.deletePostFromDatabase(post)
    goToMainActivity(context)
}

private fun goToMainActivity(context: Activity) {
    val intent = Intent(context, MainActivity::class.java)
    intent.flags =
        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    context.startActivity(intent)
}



