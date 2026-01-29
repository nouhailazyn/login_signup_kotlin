package com.example.login_signup_kotlin.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.login_signup_kotlin.R
import com.example.login_signup_kotlin.data.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var editEmail: EditText
    private lateinit var editPW: EditText
    private lateinit var tvForgetPw: TextView
    private lateinit var btnLogin: Button
    private lateinit var linkSignup: TextView
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        editEmail = findViewById(R.id.editEmail)
        editPW = findViewById(R.id.editPW)
        tvForgetPw = findViewById(R.id.tvForgetPw)
        btnLogin = findViewById(R.id.btnLogin)
        linkSignup = findViewById(R.id.linkSignup)

        db = AppDatabase.getInstance(this)

        btnLogin.setOnClickListener {
            val email = editEmail.text.toString().trim()
            val password = editPW.text.toString().trim()

            //CHECK: if empty show Toast
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter email & password!!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            //couroutine to use suspend fucntions
            val userDao = db.UserDao()
            lifecycleScope.launch(Dispatchers.IO) {
                val user = userDao.loginUser(email, password) // suspend function called in background

                launch(Dispatchers.Main) {
                    if(user != null) {
                        Toast.makeText(this@LoginActivity, "Welcome ${user.nom}", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    } else {
                        Toast.makeText(this@LoginActivity, "Email or password incorrect", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }
        // SignUp link click
        linkSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        //Forget Password click
        tvForgetPw.setOnClickListener {
            Toast.makeText(this, "Forget password clicked", Toast.LENGTH_SHORT).show()
        }
    }
}