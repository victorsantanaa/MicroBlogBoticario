package alura.com.microblogboticario.ui.theme

import alura.com.microblogboticario.R
import alura.com.microblogboticario.home.model.PostModel
import alura.com.microblogboticario.home.viewmodel.HomeViewModel
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.google.firebase.auth.FirebaseAuth


@ExperimentalComposeUiApi
@Composable
fun AlertDialogSample(
    post: PostModel, openDialog: MutableState<Boolean>,
    onDismiss: () -> Unit, viewModel: HomeViewModel
) {
    val auth = FirebaseAuth.getInstance()
    if (auth.currentUser?.email.equals(post.user_name)) {

        if (openDialog.value) {
            AlertDialog(

                title = {
                    Text("Deseja fazer alterações o item")
                },
                text = {
                    Row() {
                        Image(
                            painter = rememberImagePainter(
                                data = post.user_profile
                            ),
                            contentDescription = "Imagem do ${post.user_name}",
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .border(1.dp, Color.White, CircleShape)
                                .background(
                                    color = colorResource(
                                        id = R.color.black
                                    )
                                )
                                .align(Alignment.CenterVertically)
                        )
                        Text(
                            text = post.message_content,
                            modifier = Modifier
                                .padding(4.dp)
                                .align(Alignment.CenterVertically)
                        )
                    }
                },
                onDismissRequest = onDismiss,
                buttons = { DialogButtons(
                    post = post,
                    openDialog = openDialog,
                    viewModel = viewModel
                ) },
                modifier = Modifier
                    .fillMaxWidth()
                    .border(BorderStroke(2.dp, Color.Transparent))
                    .background(
                        color = colorResource(id = R.color.black),
                        RoundedCornerShape(10.dp)
                    )
                    .shadow(12.dp),
                properties = DialogProperties(usePlatformDefaultWidth = false)

            )
        }
    } else {
        AlertDialog(

            title = {
                Text("Alerta!!!")
            },
            text = {
                Text(text = "Você não pode remover ou editar um post que não é seu!")
            },
            onDismissRequest = onDismiss,
            confirmButton = {
                Button(
                    onClick = {
                        openDialog.value = false
                    },
                ) {
                    Text(text = "Ok")
                }
            }
        )
    }
}


@Composable
fun DialogSample(
    post: PostModel, openDialog: Boolean,
    onDismiss: () -> Unit, viewModel: HomeViewModel
) {
    Dialog(onDismissRequest = onDismiss) {

    }
}

@Composable
fun DialogButtons(openDialog : MutableState<Boolean>, viewModel: HomeViewModel, post: PostModel) {
    val navController = rememberNavController()
    Row() {
        Button(onClick = { /*onDismiss*/ }) {
            Text(text = "Cancelar")
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Editar Post")
        }
        Button(onClick = {
            viewModel.deletePostFromDatabase(post = post)
            openDialog.value = false

        }) {
            Text(text = "Deletar")
        }

    }
}

@Preview
@Composable
fun PreviewDialogButtons() {
//    DialogButtons()
}

@Composable
fun ItemListDialog(post: PostModel, viewModel: ViewModel) {
    val openDialog = remember {
        mutableStateOf(false)
    }
    Card(
        border = BorderStroke(2.dp, Color.Transparent),
        backgroundColor = colorResource(id = R.color.white_gray),
        modifier = Modifier
            .padding(6.dp)
            .fillMaxSize()
    ) {


        Column(
            modifier = Modifier
                .padding(6.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentSize(Alignment.Center)

            ) {

                Spacer(Modifier.width(5.dp))
                Text(post.user_name, style = MaterialTheme.typography.h6)

            }

            Spacer(Modifier.width(5.dp))
            Text(post.message_content, style = MaterialTheme.typography.subtitle1)

        }
    }
}
