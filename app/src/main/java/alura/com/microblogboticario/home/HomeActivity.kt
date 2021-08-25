package alura.com.microblogboticario.home

import alura.com.microblogboticario.R
import alura.com.microblogboticario.Utils
import alura.com.microblogboticario.home.model.PostModel
import alura.com.microblogboticario.news.model.NewsModel
import alura.com.microblogboticario.ui.theme.ImageListItemNews
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

@Composable
fun ScrollingListHome(list: List<PostModel>) {
    val listSize = list.size
    val scrollState = rememberLazyListState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {

        LazyColumn(state = scrollState) {
            items(listSize) {
                ItemListHome(
                    list[it].message_content,
                    list[it].user_profile,
                    list[it].message_created,
                    list[it].user_name
                )
            }
        }
    }

}

@Composable
fun ItemListHome(message: String, imageUrl: String, data: String, user: String) {
    Card(
        border = BorderStroke(2.dp, Color.Transparent),
        elevation = 12.dp,
        backgroundColor = colorResource(id = R.color.white_gray),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.padding(6.dp)
    ) {

        Column(
            modifier = Modifier.padding(6.dp)
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentSize(Alignment.Center)

            ) {
                Image(
                    painter = rememberImagePainter(
                        data = imageUrl
                    ),
                    contentDescription = "Imagem do $user",
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
                Text(user, style = MaterialTheme.typography.h6)

            }

            Spacer(Modifier.width(10.dp))
            Text(message, style = MaterialTheme.typography.subtitle1)

            Spacer(Modifier.width(10.dp))
            Text(
                data, style = MaterialTheme.typography.caption, modifier = Modifier.align(
                    Alignment.End
                )
            )
        }
    }
}

fun returnListOfPostsFake() : List<PostModel> {
    val resultList = mutableListOf<PostModel>()

    resultList.add(PostModel(
        "João",
        "https://thumbs.dreamstime.com/b/default-avatar-profile-icon-social-media-user-vector-default-avatar-profile-icon-social-media-user-vector-portrait-176194876.jpg",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ac sem sit amet metus volutpat cursus. Proin vitae leo augue. Morbi at consequat nisi. Integer eu ante at tellus laoreet lacinia. Cras at ipsum commodo, tincidunt lectus in, consectetur purus. Quisque congue consequat ultricies.",
        "OnTEM",
        1
    ))

    resultList.add(PostModel(
        "Miguel",
        "https://thumbs.dreamstime.com/b/default-avatar-profile-icon-social-media-user-vector-default-avatar-profile-icon-social-media-user-vector-portrait-176194876.jpg",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ac sem sit amet metus volutpat cursus. Proin vitae leo augue. Morbi at consequat nisi. Integer eu ante at tellus laoreet lacinia. Cras at ipsum commodo, tincidunt lectus in, consectetur purus. Quisque congue consequat ultricies.",
        "OnTEM",
        2
    ))

    resultList.add(PostModel(
        "Maria",
        "https://thumbs.dreamstime.com/b/default-avatar-profile-icon-social-media-user-vector-default-avatar-profile-icon-social-media-user-vector-portrait-176194876.jpg",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ac sem sit amet metus volutpat cursus. Proin vitae leo augue. Morbi at consequat nisi. Integer eu ante at tellus laoreet lacinia. Cras at ipsum commodo, tincidunt lectus in, consectetur purus. Quisque congue consequat ultricies.",
        "OnTEM",
        3
    ))

    resultList.add(PostModel(
        "Carolina",
        "https://thumbs.dreamstime.com/b/default-avatar-profile-icon-social-media-user-vector-default-avatar-profile-icon-social-media-user-vector-portrait-176194876.jpg",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ac sem sit amet metus volutpat cursus. Proin vitae leo augue. Morbi at consequat nisi. Integer eu ante at tellus laoreet lacinia. Cras at ipsum commodo, tincidunt lectus in, consectetur purus. Quisque congue consequat ultricies.",
        "OnTEM",
        4
    ))
    resultList.add(PostModel(
        "Victor",
        "https://thumbs.dreamstime.com/b/default-avatar-profile-icon-social-media-user-vector-default-avatar-profile-icon-social-media-user-vector-portrait-176194876.jpg",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ac sem sit amet metus volutpat cursus. Proin vitae leo augue. Morbi at consequat nisi. Integer eu ante at tellus laoreet lacinia. Cras at ipsum commodo, tincidunt lectus in, consectetur purus. Quisque congue consequat ultricies.",
        "OnTEM",
        5
    ))
    resultList.add(PostModel(
        "Oswaldo",
        "https://thumbs.dreamstime.com/b/default-avatar-profile-icon-social-media-user-vector-default-avatar-profile-icon-social-media-user-vector-portrait-176194876.jpg",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ac sem sit amet metus volutpat cursus. Proin vitae leo augue. Morbi at consequat nisi. Integer eu ante at tellus laoreet lacinia. Cras at ipsum commodo, tincidunt lectus in, consectetur purus. Quisque congue consequat ultricies.",
        "OnTEM",
        6
    ))
    resultList.add(PostModel(
        "Lucas",
        "https://thumbs.dreamstime.com/b/default-avatar-profile-icon-social-media-user-vector-default-avatar-profile-icon-social-media-user-vector-portrait-176194876.jpg",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ac sem sit amet metus volutpat cursus. Proin vitae leo augue. Morbi at consequat nisi. Integer eu ante at tellus laoreet lacinia. Cras at ipsum commodo, tincidunt lectus in, consectetur purus. Quisque congue consequat ultricies.",
        "OnTEM",
        7
    ))
    resultList.add(PostModel(
        "Eubertron",
        "https://thumbs.dreamstime.com/b/default-avatar-profile-icon-social-media-user-vector-default-avatar-profile-icon-social-media-user-vector-portrait-176194876.jpg",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ac sem sit amet metus volutpat cursus. Proin vitae leo augue. Morbi at consequat nisi. Integer eu ante at tellus laoreet lacinia. Cras at ipsum commodo, tincidunt lectus in, consectetur purus. Quisque congue consequat ultricies.",
        "OnTEM",
        8
    ))
    return resultList
}

@Preview
@Composable
fun PreviewListHome() {
    ScrollingListHome(list = returnListOfPostsFake())
}