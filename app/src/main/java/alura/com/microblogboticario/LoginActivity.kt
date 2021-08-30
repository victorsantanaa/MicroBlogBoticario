package alura.com.microblogboticario

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest

class LoginActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    private lateinit var registerLayout: ConstraintLayout
    private lateinit var registerUserName: TextInputEditText
    private lateinit var registerEmail: TextInputEditText
    private lateinit var registerPassword: TextInputEditText
    private lateinit var registerConfirmPassword: TextInputEditText
    private lateinit var registerButtonOtherAccount: Button
    private lateinit var registerButtonRegister: Button

    private lateinit var loginLayout: ConstraintLayout
    private lateinit var loginEmail: TextInputEditText
    private lateinit var loginPassword: TextInputEditText
    private lateinit var loginButtonRegister: Button
    private lateinit var loginButtonSignIn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()


        registerLayout = findViewById(R.id.layout_register)
        registerUserName = findViewById(R.id.register_user_name)
        registerEmail = findViewById(R.id.register_email_input)
        registerPassword = findViewById(R.id.register_password_input)
        registerConfirmPassword = findViewById(R.id.register_confirm_password_input)
        registerButtonOtherAccount = findViewById(R.id.register_outra_conta_button)
        registerButtonRegister = findViewById(R.id.register_registrar_button)

        loginLayout = findViewById(R.id.layout_login)
        loginEmail = findViewById(R.id.login_email_input)
        loginPassword = findViewById(R.id.login_password_input)
        loginButtonRegister = findViewById(R.id.login_registrar_button)
        loginButtonSignIn = findViewById(R.id.login_entrar_button)

        val progressBar = findViewById<ProgressBar>(R.id.login_progress)

        loginButtonRegister.setOnClickListener {
            registerLayout.visibility = View.VISIBLE
            loginLayout.visibility = View.GONE
        }

        registerButtonOtherAccount.setOnClickListener {
            registerLayout.visibility = View.GONE
            loginLayout.visibility = View.VISIBLE
        }
        registerButtonRegister.setOnClickListener {
            register(
                registerUserName.text.toString(),
                registerEmail.text.toString(),
                registerPassword.text.toString(),
                registerConfirmPassword.text.toString(),
                progressBar
            )
        }
        loginButtonSignIn.setOnClickListener {
            login(
                loginEmail.text.toString(),
                loginPassword.text.toString(),
                progressBar
            )
        }

    }

    private fun register(
        registerName: String,
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

                            mAuth.currentUser?.updateProfile(
                                UserProfileChangeRequest.Builder().setDisplayName(registerName).build()
                            )
                            goToHome()
                        } else {
                            val error = task.exception?.message
                            Toast.makeText(
                                this, "Erro no cadastro: $error",
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