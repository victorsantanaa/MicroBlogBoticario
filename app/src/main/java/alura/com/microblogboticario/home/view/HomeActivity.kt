package alura.com.microblogboticario.home.view

import alura.com.microblogboticario.R
import alura.com.microblogboticario.home.model.PostModel
import alura.com.microblogboticario.home.viewmodel.HomeViewModel
import android.content.Context
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import coil.compose.rememberImagePainter
import com.google.firebase.auth.FirebaseAuth

@ExperimentalComposeUiApi
@Composable
fun HomeScreen(
    list: List<PostModel>,
    auth: FirebaseAuth,
    homeViewModel: HomeViewModel,
    context: Context
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.93f)
            .background(colorResource(id = R.color.white))
            .wrapContentSize(Alignment.Center)
    ) {

        ScrollingListHome(list = list.asReversed(), homeViewModel, context)
        ButtonLogout(auth)
    }
}

@ExperimentalComposeUiApi
@Composable
fun ScrollingListHome(
    list: List<PostModel>,
    viewModel: ViewModel,
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
    context: Context
) {
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
            "Astri Ørmen",
            "https://randomuser.me/api/portraits/med/women/81.jpg",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ac sem sit amet metus volutpat cursus. Proin vitae leo augue. Morbi at consequat nisi.",
            "17h50 - 20/07/2021",
            1
        )
    )

    resultList.add(
        PostModel(
            "Lélia Caldeira",
            "https://randomuser.me/api/portraits/med/women/0.jpg",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit.  Integer eu ante at tellus laoreet lacinia. Cras at ipsum commodo, tincidunt lectus in, consectetur purus. Quisque congue consequat ultricies.",
            "20h12 - 20/07/2021",
            2
        )
    )

    resultList.add(
        PostModel(
            "Aaron Laurent",
            "https://randomuser.me/api/portraits/med/men/66.jpg",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ac sem sit amet metus volutpat cursus. Proin vitae leo augue. Morbi at consequat nisi. Integer eu ante at tellus laoreet lacinia. Cras at ipsum commodo, tincidunt lectus in, consectetur purus. Quisque congue consequat ultricies.",
            "09h43 - 21/07/2021",
            3
        )
    )

    resultList.add(
        PostModel(
            "Linnea Haapala",
            "https://randomuser.me/api/portraits/med/women/20.jpg",
            "Nunc ac sem sit amet metus volutpat cursus. Proin vitae leo augue. Morbi at consequat nisi. Integer eu ante at tellus laoreet lacinia. Cras at ipsum commodo, tincidunt lectus in, consectetur purus. Quisque congue consequat ultricies.",
            "09h50 - 21/07/2021",
            4
        )
    )
    resultList.add(
        PostModel(
            "Guilherme Lima",
            "https://randomuser.me/api/portraits/med/men/0.jpg",
            "Proin vitae leo augue. Morbi at consequat nisi.",
            "12h00 - 23/07/2021",
            5
        )
    )
    resultList.add(
        PostModel(
            "Lucas Chow",
            "https://randomuser.me/api/portraits/med/men/18.jpg",
            "Nunc ac sem sit amet metus volutpat cursus. Proin vitae leo augue. Cras at ipsum commodo, tincidunt lectus in, consectetur purus. Quisque congue consequat ultricies.",
            "00h00 - 01/08/2021",
            6
        )
    )
    resultList.add(
        PostModel(
            "James Gauthier",
            "https://randomuser.me/api/portraits/med/men/48.jpg",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ac sem sit amet metus volutpat cursus.Integer eu ante at tellus laoreet lacinia. Cras at ipsum commodo, tincidunt lectus in, consectetur purus. Quisque congue consequat ultricies.",
            "00h03 - 01/08/2021",
            7
        )
    )
    resultList.add(
        PostModel(
            "Emmeli Thøgersen",
            "https://randomuser.me/api/portraits/med/women/4.jpg",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vitae leo augue. Morbi at consequat nisi. Cras at ipsum commodo, tincidunt lectus in, consectetur purus. Quisque congue consequat ultricies.",
            "11h35 - 26/08/2021",
            8
        )
    )
    return resultList
}