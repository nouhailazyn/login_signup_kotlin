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
import com.example.login_signup_kotlin.data.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpActivity : AppCompatActivity() {
    private lateinit var editFullNameSignup: EditText
    private lateinit var editEmailSignup: EditText
    private lateinit var editPwSignup: EditText
    private lateinit var editPwConfir: EditText
    private lateinit var btnSignup: Button
    private lateinit var linkSignup: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        editFullNameSignup = findViewById(R.id.editFullNameSignup)
        editEmailSignup = findViewById(R.id.editEmailSignup)
        editPwSignup = findViewById(R.id.editPwSignup)
        editPwConfir = findViewById(R.id.editPwConfir)
        btnSignup = findViewById(R.id.btnSignup)
        linkSignup = findViewById(R.id.linkSignup)

        val db = AppDatabase.getInstance(this)
        val userDao = db.UserDao()

        btnSignup.setOnClickListener {
            val fullName = editFullNameSignup.text.toString().trim()
            val emailSign = editEmailSignup.text.toString().trim()
            val pwSign = editPwSignup.text.toString().trim()
            val confPw = editPwConfir.text.toString().trim()

            if (fullName.isEmpty() || emailSign.isEmpty() || pwSign.isEmpty() || confPw.isEmpty()) {
                Toast.makeText(this, "Please fill all the fields!!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //CHECK: regex email
            val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
            if (!emailSign.matches(emailPattern.toRegex())) {
                editEmailSignup.error = "Invalide email format"
                return@setOnClickListener
            }

            //CHECK: length password
            if (pwSign.length < 6) {
                editPwSignup.error = "Password must be at least 6 characters"
                //Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            //CHECK: regex password
            val passwordPattern = """^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{6,}$"""
            if (!pwSign.matches(passwordPattern.toRegex())) {
                editPwSignup.error = "Weak password: must include A-Z, a-z, 0-9 & symbol"
                return@setOnClickListener
            }
            //CHECK: password confirmation
            if(pwSign != confPw) {
                editPwConfir.error = "Password do not match!!"
                return@setOnClickListener
            }

            //Insert user in DB asynchronsly
            lifecycleScope.launch(Dispatchers.IO) {
                //CHECK: if email deja exist and didn't alowed the user to using it again
                val existingUser = userDao.loginUser(emailSign, pwSign)
                if(existingUser != null) {
                    editEmailSignup.error = "Email already registered"
                }else{
                    //Create a user object
                    val newUser = User(
                        nom = fullName,
                        prenom = "",
                        telephone = "",
                        email = emailSign,
                        password = pwSign,
                        villeId = null
                    )
                    userDao.upsertUser(newUser)

                    // return to UI thread to show toast and move to login
                    launch(Dispatchers.Main) {
                        Toast.makeText(this@SignUpActivity, "Registration successful!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@SignUpActivity, LoginActivity::class.java))
                    }
                }
            }
        }

        linkSignup.setOnClickListener {
            startActivity(Intent(this@SignUpActivity, LoginActivity::class.java))
            finish()
        }
    }
}