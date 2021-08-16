package alura.com.microblogboticario

import alura.com.microblogboticario.ui.theme.SplashScreen
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth

abstract class SplashActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mAuth = FirebaseAuth.getInstance()
        val user = mAuth.currentUser

        setFlags()

        if (user != null) {
            Toast.makeText(applicationContext, "Bem vindo de volta"
                    + user.displayName, Toast.LENGTH_SHORT).show()
        } else {
            val handle = Handler()
            handle.postDelayed(Runnable { mostrarHome() }, 2000)
        }





    }

    private fun setFlags() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

    fun mostrarHome() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}

