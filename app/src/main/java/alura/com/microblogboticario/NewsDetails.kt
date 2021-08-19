package alura.com.microblogboticario

import alura.com.microblogboticario.ui.theme.ImageItem
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun NewsDetailHeader(
    image: String,
    userName: String,
    content: String,
    created: String
) {
    Column {
        Row {
            ImageItem(url = image)
            Text(text = userName)
        }
        Text(text = content)
        Text(text = created)
    }
}

@Preview
@Composable
fun PreviewNewsDetailHeader() {
    NewsDetailHeader(image = "https://developer.android.com/images/brand/Android_Robot.png",
        userName = "João",
        content = "Aqui está uma mensagem",
        created = "Criado há um tempo")
}