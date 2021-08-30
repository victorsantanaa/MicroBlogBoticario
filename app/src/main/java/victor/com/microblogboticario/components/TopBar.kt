package victor.com.microblogboticario.components

import victor.com.microblogboticario.LoginActivity
import victor.com.microblogboticario.R
import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth

@Composable
fun TopBar(context: Activity) {

    val openDialog = remember {
        mutableStateOf(false)
    }

    TopAppBar(
        title = {
            Row(modifier = Modifier
                .clickable {

                    openDialog.value = true

                }
                .padding(6.dp)) {

                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    imageVector = Icons.Default.ExitToApp,
                    contentDescription = "Botão de sair",
                    modifier = Modifier
                        .padding(start = 4.dp)
                )
            }
        },
        backgroundColor = colorResource(id = R.color.black),
        contentColor = Color.White
    )
    if (openDialog.value) {
        AlertDialog(
            title = {
                Text("Você será deslogado!")
            },
            text = { Text("Deseja realmente sair da conta?") },
            onDismissRequest = { openDialog.value = false },
            modifier = Modifier
                .fillMaxWidth()
                .border(BorderStroke(2.dp, Color.Transparent))
                .shadow(12.dp),
            confirmButton = {
                TextButton(onClick = {
                    logout(context)
                    openDialog.value = false
                }) {
                    Text(text = "Sair", color = Color.Black)
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

private fun logout(context: Activity) {
    Toast
        .makeText(context, "Saindo", Toast.LENGTH_SHORT)
        .show()
    FirebaseAuth
        .getInstance()
        .signOut()
    val intent = Intent(context, LoginActivity::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    context.startActivity(intent)
}

@Composable
fun DetailTopBar(activity: Activity) {
    TopAppBar(
        title = {},
        backgroundColor = colorResource(id = R.color.black),
        contentColor = Color.White,
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "back button",
                modifier = Modifier
                    .clickable { activity.finish() }
                    .padding(5.dp)
            )
        }
    )
}