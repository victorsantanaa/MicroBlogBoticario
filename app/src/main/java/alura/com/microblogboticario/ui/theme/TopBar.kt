package alura.com.microblogboticario.ui.theme

import alura.com.microblogboticario.LoginActivity
import alura.com.microblogboticario.MainActivity
import alura.com.microblogboticario.R
import alura.com.microblogboticario.home.ProfileActivity
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth

@Composable
fun TopBar(context: Activity) {

    TopAppBar(

        title = {
            Row(modifier = Modifier.clickable {

                Toast.makeText(context, "Saindo", Toast.LENGTH_SHORT).show()
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(context, LoginActivity::class.java)
                intent.flags =  Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                context.startActivity(intent)

            }.padding(6.dp)) {

                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    imageVector = Icons.Default.ExitToApp,
                    contentDescription = "Menu Perfil",
                    modifier = Modifier
                        .padding(start = 4.dp)
                )
            }
        },
        backgroundColor = colorResource(id = R.color.black),
        contentColor = Color.White
    )
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

@Composable
fun ProfileTopBar(activity: Activity) {
    TopAppBar(

        title = {
            Row(modifier = Modifier.clickable {
                activity.startActivity(Intent(activity, MainActivity::class.java))
                activity.overridePendingTransition(
                    android.R.anim.fade_in,
                    R.anim.slide_up
                )
                activity.finish()
            }.padding(6.dp)) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = "Voltar",
                    modifier = Modifier
                        .padding(start = 4.dp)
                )
            }
        },
        backgroundColor = colorResource(id = R.color.black),
        contentColor = Color.White
    )
}
