package alura.com.microblogboticario

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.view.marginBottom
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var passwordConfirm: AppCompatEditText
    private lateinit var buttonRegister: Button
    private lateinit var mAuth: FirebaseAuth
    private var isRegister = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

        val email = findViewById<AppCompatAutoCompleteTextView>(R.id.login_email_input)
        val password = findViewById<AppCompatEditText>(R.id.login_password_input)
        passwordConfirm = findViewById(R.id.login_confirm_password_input)
        val buttonLogin = findViewById<Button>(R.id.login_sign_in_button)
        buttonRegister = findViewById(R.id.login_sign_up_button)
        val checkBox = findViewById<CheckBox>(R.id.login_checkbox)
        val progressBar = findViewById<ProgressBar>(R.id.login_progress)

        buttonLoginConfig(buttonLogin, email, password, passwordConfirm, progressBar)

        buttonRegister.setOnClickListener {
            buttonRegister.visibility = View.GONE
            passwordConfirm.visibility = View.VISIBLE
            isRegister = true
        }

        checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                password.transformationMethod = HideReturnsTransformationMethod.getInstance()
                if (passwordConfirm.visibility == View.VISIBLE) {
                    passwordConfirm.transformationMethod =
                        HideReturnsTransformationMethod.getInstance()
                }
            } else {
                password.transformationMethod = PasswordTransformationMethod.getInstance()
                if (passwordConfirm.visibility == View.VISIBLE) {
                    passwordConfirm.transformationMethod =
                        HideReturnsTransformationMethod.getInstance()
                }
            }
        }

    }

    override fun onBackPressed() {
        if (isRegister) {
            isRegister = false
            buttonRegister.visibility = View.VISIBLE
            passwordConfirm.visibility = View.GONE
        } else {
            super.onBackPressed()
        }

    }

    private fun buttonLoginConfig(
        buttonLogin: Button,
        email: AppCompatAutoCompleteTextView,
        password: AppCompatEditText,
        confirmPassword: AppCompatEditText,
        progressBar: ProgressBar
    ) {
        buttonLogin.setOnClickListener {
            val loginEmail = email.text.toString()
            val loginPassword = password.text.toString()
            val registerConfirmPassword = confirmPassword.text.toString()


            if (isRegister) {
                register(
                    loginEmail,
                    loginPassword,
                    registerConfirmPassword,
                    progressBar
                )
            } else {
                login(loginEmail, loginPassword, progressBar)
            }

        }
    }

    private fun register(
        loginEmail: String,
        loginPassword: String,
        registerConfirmPassword: String,
        progressBar: ProgressBar
    ) {
        if (
            !TextUtils.isEmpty(loginEmail) &&
            !TextUtils.isEmpty(loginPassword) &&
            !TextUtils.isEmpty(registerConfirmPassword)
        ) {
            if (loginPassword == registerConfirmPassword) {

                progressBar.visibility = View.VISIBLE
                mAuth.createUserWithEmailAndPassword(loginEmail, loginPassword)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            goToHome()
                        } else {
                            val error = task.exception?.message
                            Toast.makeText(
                                this, "Erro no cadastro: " + error,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        progressBar.visibility = View.GONE
                    }

            } else {
                Toast.makeText(
                    this, "A senha deve ser a mesma em ambos os campos",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun login(
        loginEmail: String,
        loginPassword: String,
        progressBar: ProgressBar
    ) {
        if (!TextUtils.isEmpty(loginEmail) && !TextUtils.isEmpty(loginPassword)) {
            progressBar.visibility = View.VISIBLE
            mAuth.signInWithEmailAndPassword(loginEmail, loginPassword)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        goToHome()
                    } else {
                        val error = task.exception?.message
                        Toast.makeText(this, "Erro no Login: $error", Toast.LENGTH_SHORT)
                            .show()
                        progressBar.visibility = View.GONE
                    }
                }
        }
    }

    private fun goToHome() {
        startActivity(Intent(this, MainActivity::class.java))
    }


}