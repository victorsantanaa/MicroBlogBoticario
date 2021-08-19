package alura.com.microblogboticario.ui.theme

import alura.com.microblogboticario.model.NewsModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter

//"https://developer.android.com/images/brand/Android_Robot.png"

@Composable
fun ImageItem(url: String) {
    Image(
        painter = rememberImagePainter(
            data = url
        ),

        contentDescription = "imagem",
        modifier = Modifier
            .size(50.dp)
            .padding(8.dp)
    )
}

@Preview
@Composable
fun PreviewImageItem(){
    ImageItem(url = "https://developer.android.com/images/brand/Android_Robot.png")
}

@Composable
fun TextListItem(message: String, author: String, created: String, modifier: Modifier) {
    Column(modifier = modifier) {
        Text(text = message, style = MaterialTheme.typography.subtitle1)

        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = "by $author | $created",
                style = MaterialTheme.typography.body2
            )
        }
    }
}

@Preview
@Composable
fun PreviewTextListItem(){
    TextListItem(
        message = "mensagem aqui vamo que vamo que vamo",
        author = "autor",
        created = "ontem",
        modifier = Modifier)
}

@Composable
fun ItemList(
    image:String,
    message: String,
    author: String,
    created: String,
    modifier: Modifier
){
    Card(modifier = modifier) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            ImageItem(url = image)
            TextListItem(
                message = message,
                author = author,
                created = created,
                modifier = modifier
            )
        }
    }
}

@Preview
@Composable
fun PreviewItemList(){
    ItemList(image = "https://developer.android.com/images/brand/Android_Robot.png",
        message = "mensagem aqui vamo que vamo que vamo",
        author = "autor",
        created = "ontem",
        modifier = Modifier
    )
}

@Composable
fun ItemList(
    news: NewsModel,
    onItemClicked:(news: NewsModel) -> Unit,
    navController: NavController
) {
    ItemList(
        image = news.user.profilePicture,
        message = news.message.content,
        author = news.user.name,
        created = news.message.createdAt, modifier =Modifier
            .padding(8.dp)
            .clickable {
                onItemClicked(news)
                navController.navigate("newsDetails")
            }
    )
}

