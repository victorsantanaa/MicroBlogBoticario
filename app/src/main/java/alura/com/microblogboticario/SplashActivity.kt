package alura.com.microblogboticario

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

//        FirebaseApp.initializeApp(this)
        mAuth = FirebaseAuth.getInstance()
        val user = mAuth.currentUser

        setFlags()
        val handle = Handler()

        if (user != null) {
            Toast.makeText(applicationContext, "Bem vindo de volta"
                    + user.displayName, Toast.LENGTH_SHORT).show()

            handle.postDelayed({ mostrarHome() }, 2000)
        } else {
            handle.postDelayed({ mostrarLogin() }, 2000)
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

    fun mostrarLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}

