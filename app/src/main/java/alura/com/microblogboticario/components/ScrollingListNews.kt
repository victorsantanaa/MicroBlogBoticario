package alura.com.microblogboticario.components

import alura.com.microblogboticario.R
import alura.com.microblogboticario.utils.Utils
import alura.com.microblogboticario.news.model.NewsModel
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter


@Composable
fun ImageListItemNews(index: Int, message: String, imageUrl: String, data: String, user: String) {
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
                Text(message, style = MaterialTheme.typography.subtitle1)

            }

            Spacer(Modifier.width(10.dp))
            Text(
                data, style = MaterialTheme.typography.caption, modifier = Modifier.align(
                    Alignment.End
                )
            )
        }
    }
}

@Composable
fun ScrollingListNews(list: List<NewsModel>) {
    val listSize = list.size
    // We save the scrolling position with this state
    val scrollState = rememberLazyListState()
    // We save the coroutine scope where our animated scroll will be executed
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {

        LazyColumn(state = scrollState) {
            items(listSize) {
                ImageListItemNews(
                    it,
                    list[it].message_content,
                    list[it].user_profile,
                    Utils.formatDate(list[it].message_created),
                    list[it].user_name
                )
            }
        }
    }

}