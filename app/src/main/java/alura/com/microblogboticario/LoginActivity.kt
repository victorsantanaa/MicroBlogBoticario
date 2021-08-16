package alura.com.microblogboticario

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Build

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor
        }
    }
}