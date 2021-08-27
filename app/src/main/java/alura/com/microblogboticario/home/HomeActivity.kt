package alura.com.microblogboticario.home

import alura.com.microblogboticario.R
import alura.com.microblogboticario.home.model.PostModel
import alura.com.microblogboticario.home.viewmodel.HomeViewModel
import alura.com.microblogboticario.ui.theme.AlertDialogSample
import alura.com.microblogboticario.ui.theme.NavigationItem
import alura.com.microblogboticario.ui.theme.ui.theme.ButtonLogout
import android.content.Context
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

@ExperimentalComposeUiApi
@Composable
fun HomeScreen(
    list: List<PostModel>,
    auth: FirebaseAuth,
    homeViewModel: HomeViewModel,
    navController: NavHostController,
    context: Context
) {

    val owner = LocalLifecycleOwner.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
            .wrapContentSize(Alignment.Center)
    ) {

        ScrollingListHome(list = list.asReversed(), homeViewModel, navController, context)
        ButtonLogout(auth)
    }
}

@ExperimentalComposeUiApi
@Composable
fun ScrollingListHome(
    list: List<PostModel>,
    viewModel: ViewModel,
    navController: NavHostController,
    context: Context
) {
    val listSize = list.size
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {

        LazyColumn {
            items(listSize) {
                ItemListHome(
                    list[it],
                    viewModel,
                    navController,
                    context
                )
            }
        }
    }

}

@ExperimentalComposeUiApi
@Composable
fun ItemListHome(
    post: PostModel,
    viewModel: ViewModel,
    navController: NavHostController,
    context: Context
) {


    val openDialog = remember {
        mutableStateOf(false)
    }
    val homeViewModel = viewModel as HomeViewModel
    Card(
        border = BorderStroke(2.dp, Color.Transparent),
        elevation = 12.dp,
        backgroundColor = colorResource(id = R.color.white_gray),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .padding(6.dp)
            .fillMaxWidth()
    ) {

        if (openDialog.value) {
            AlertDialogSample(
                post = post,
                openDialog = openDialog,
                onDismiss = { openDialog.value = false },
                viewModel = homeViewModel
            )
        }

        Column(
            modifier = Modifier
                .padding(6.dp)
                .clickable {
                    context.startActivity(DetailActivity.newIntent(context, post))
                    homeViewModel.updateIdState(post.id.toInt())

                }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentSize(Alignment.Center)

            ) {
                Image(
                    painter = rememberImagePainter(
                        data = post.user_profile
                    ),
                    contentDescription = "Imagem do ${post.user_name}",
                    modifier = Modifier
                        .size(65.dp)
                        .clip(CircleShape)
                        .border(1.dp, Color.White, CircleShape)
                        .background(
                            color = colorResource(
                                id = R.color.boticario_background
                            )
                        )
                )
                Spacer(Modifier.width(10.dp))
                Text(post.user_name, style = MaterialTheme.typography.h6)

            }

            Spacer(Modifier.width(10.dp))
            Text(post.message_content, style = MaterialTheme.typography.subtitle1)

            Spacer(Modifier.width(10.dp))
            Text(
                post.message_created,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.align(
                    Alignment.End
                )
            )
        }
    }
}

fun returnListOfPostsFake(): List<PostModel> {
    val resultList = mutableListOf<PostModel>()

    resultList.add(
        PostModel(
            "João",
            "https://thumbs.dreamstime.com/b/default-avatar-profile-icon-social-media-user-vector-default-avatar-profile-icon-social-media-user-vector-portrait-176194876.jpg",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ac sem sit amet metus volutpat cursus. Proin vitae leo augue. Morbi at consequat nisi. Integer eu ante at tellus laoreet lacinia. Cras at ipsum commodo, tincidunt lectus in, consectetur purus. Quisque congue consequat ultricies.",
            "OnTEM",
            1
        )
    )

    resultList.add(
        PostModel(
            "Miguel",
            "https://thumbs.dreamstime.com/b/default-avatar-profile-icon-social-media-user-vector-default-avatar-profile-icon-social-media-user-vector-portrait-176194876.jpg",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ac sem sit amet metus volutpat cursus. Proin vitae leo augue. Morbi at consequat nisi. Integer eu ante at tellus laoreet lacinia. Cras at ipsum commodo, tincidunt lectus in, consectetur purus. Quisque congue consequat ultricies.",
            "OnTEM",
            2
        )
    )

    resultList.add(
        PostModel(
            "Maria",
            "https://thumbs.dreamstime.com/b/default-avatar-profile-icon-social-media-user-vector-default-avatar-profile-icon-social-media-user-vector-portrait-176194876.jpg",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ac sem sit amet metus volutpat cursus. Proin vitae leo augue. Morbi at consequat nisi. Integer eu ante at tellus laoreet lacinia. Cras at ipsum commodo, tincidunt lectus in, consectetur purus. Quisque congue consequat ultricies.",
            "OnTEM",
            3
        )
    )

    resultList.add(
        PostModel(
            "Carolina",
            "https://thumbs.dreamstime.com/b/default-avatar-profile-icon-social-media-user-vector-default-avatar-profile-icon-social-media-user-vector-portrait-176194876.jpg",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ac sem sit amet metus volutpat cursus. Proin vitae leo augue. Morbi at consequat nisi. Integer eu ante at tellus laoreet lacinia. Cras at ipsum commodo, tincidunt lectus in, consectetur purus. Quisque congue consequat ultricies.",
            "OnTEM",
            4
        )
    )
    resultList.add(
        PostModel(
            "Victor",
            "https://thumbs.dreamstime.com/b/default-avatar-profile-icon-social-media-user-vector-default-avatar-profile-icon-social-media-user-vector-portrait-176194876.jpg",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ac sem sit amet metus volutpat cursus. Proin vitae leo augue. Morbi at consequat nisi. Integer eu ante at tellus laoreet lacinia. Cras at ipsum commodo, tincidunt lectus in, consectetur purus. Quisque congue consequat ultricies.",
            "OnTEM",
            5
        )
    )
    resultList.add(
        PostModel(
            "Oswaldo",
            "https://thumbs.dreamstime.com/b/default-avatar-profile-icon-social-media-user-vector-default-avatar-profile-icon-social-media-user-vector-portrait-176194876.jpg",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ac sem sit amet metus volutpat cursus. Proin vitae leo augue. Morbi at consequat nisi. Integer eu ante at tellus laoreet lacinia. Cras at ipsum commodo, tincidunt lectus in, consectetur purus. Quisque congue consequat ultricies.",
            "OnTEM",
            6
        )
    )
    resultList.add(
        PostModel(
            "Lucas",
            "https://thumbs.dreamstime.com/b/default-avatar-profile-icon-social-media-user-vector-default-avatar-profile-icon-social-media-user-vector-portrait-176194876.jpg",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ac sem sit amet metus volutpat cursus. Proin vitae leo augue. Morbi at consequat nisi. Integer eu ante at tellus laoreet lacinia. Cras at ipsum commodo, tincidunt lectus in, consectetur purus. Quisque congue consequat ultricies.",
            "OnTEM",
            7
        )
    )
    resultList.add(
        PostModel(
            "Eubertron",
            "https://thumbs.dreamstime.com/b/default-avatar-profile-icon-social-media-user-vector-default-avatar-profile-icon-social-media-user-vector-portrait-176194876.jpg",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ac sem sit amet metus volutpat cursus. Proin vitae leo augue. Morbi at consequat nisi. Integer eu ante at tellus laoreet lacinia. Cras at ipsum commodo, tincidunt lectus in, consectetur purus. Quisque congue consequat ultricies.",
            "OnTEM",
            8
        )
    )
    return resultList
}