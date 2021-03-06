package victor.com.microblogboticario

import victor.com.microblogboticario.home.model.PostModel
import victor.com.microblogboticario.home.viewmodel.HomeViewModel
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mAuth = FirebaseAuth.getInstance()
        val user = mAuth.currentUser

        homeViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(application)
        )
            .get(HomeViewModel::class.java)

        val listPost: MutableList<PostModel> = mutableListOf()

        homeViewModel.putFakeOnDatabase()
        homeViewModel.getAllPostList().observe(this, { newList ->
            listPost.addAll(newList)
        })



        setFlags()
        val handle = Handler()

        if (user != null) {
            Toast.makeText(applicationContext, "Bem vindo de volta "
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

