package alura.com.microblogboticario.home.view

import alura.com.microblogboticario.LoginActivity
import alura.com.microblogboticario.R
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.widget.ConstraintLayout
import coil.compose.rememberImagePainter
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import de.hdodenhof.circleimageview.CircleImageView

class ProfileActivity : AppCompatActivity() {

    private lateinit var userNameContainer: ConstraintLayout
    private lateinit var userNameText: TextView
    private lateinit var userEditName: ImageButton
    private lateinit var logoutButton: Button

    private lateinit var imagePicture: CircleImageView
    private lateinit var imageEditPicture: CircleImageView

    private lateinit var editUserNameContainer: ConstraintLayout
    private lateinit var userEditText: TextInputEditText
    private lateinit var confirmEditName: ImageButton
    private lateinit var cancelEditName: ImageButton

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val nomeDoUsuario = "Victor Santana Ribeiro"

        auth = FirebaseAuth.getInstance()
        changeUserName(nomeDoUsuario)
        overridePendingTransition(R.anim.slide_down_profile, R.anim.no_animation)

        userNameText = findViewById(R.id.profile_user_name)
        userEditName = findViewById(R.id.profile_edit_name_button)
        logoutButton = findViewById(R.id.profile_button_logout)
        userNameContainer = findViewById(R.id.profile_name_layout)

        editUserNameContainer = findViewById(R.id.profile_edit_name_layout)
        userEditText = findViewById(R.id.profile_edit_name_text)
        confirmEditName = findViewById(R.id.profile_button_confirm_edit_name)
        cancelEditName = findViewById(R.id.profile_button_cancel_edit_name)

        imagePicture = findViewById(R.id.profile_picture)
        imageEditPicture = findViewById(R.id.profile_edit_picture)

        userNameText.text = auth.currentUser?.displayName.toString()

        userEditName.setOnClickListener {
            editUserNameContainer.visibility = View.VISIBLE
            userNameContainer.visibility = View.GONE
            userEditText.text = userNameText.editableText
        }

        cancelEditName.setOnClickListener {
            editUserNameContainer.visibility = View.GONE
            userNameContainer.visibility = View.VISIBLE
            userEditText.text = null

        }

        confirmEditName.setOnClickListener {
            editUserNameContainer.visibility = View.GONE
            userNameContainer.visibility = View.VISIBLE
            changeUserName(userEditText.text.toString())
            userNameText.text = auth.currentUser?.displayName
        }





    }

    private fun changeUserName(nomeDoUsuario: String) {
        val user = auth.currentUser
        user?.updateProfile(
            UserProfileChangeRequest.Builder().setDisplayName(nomeDoUsuario).build()
        )
    }


}

@Composable
fun ProfileScreen(auth: FirebaseAuth) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.white_gray)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProfileHeader()
        ButtonLogout(auth = auth)

        Row() {
            ProfileName(auth = auth)

        }
    }

}

@Composable
fun ProfileName(auth: FirebaseAuth) {
    Column() {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = auth.currentUser?.displayName!!)
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Editar Nome",
                modifier = Modifier
                    .padding(start = 4.dp)
            )
        }

    }
}

@Composable
fun ProfileHeader() {
    Column(
        modifier = Modifier

            .fillMaxWidth(0.8f)
            .fillMaxHeight(0.8f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberImagePainter(
                data = "https://thumbs.dreamstime.com/b/default-avatar-profile-icon-social-media-user-vector-default-avatar-profile-icon-social-media-user-vector-portrait-176194876.jpg"
            ),
            contentDescription = "Imagem de Perfil",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .clip(CircleShape)
                .border(2.dp, colorResource(R.color.themedark_chumbo), CircleShape)
                .background(
                    color = colorResource(
                        id = R.color.boticario_background
                    )
                )
                .align(Alignment.CenterHorizontally)
        )
    }
}


@Composable
fun ButtonLogout(auth: FirebaseAuth) {
    val context = LocalContext.current

    OutlinedButton(
        onClick = {
            Toast.makeText(context, "Saindo", Toast.LENGTH_SHORT).show()
            auth.signOut()
            context.startActivity(Intent(context, LoginActivity::class.java))

        },
        modifier = Modifier.background(Color.Transparent),

        ) {
        Text(text = "Sair da conta", color = Color.Black)

    }
}