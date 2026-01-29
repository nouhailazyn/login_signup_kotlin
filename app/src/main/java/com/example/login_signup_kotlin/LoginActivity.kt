package com.example.login_signup_kotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var editEmail: EditText
    private lateinit var editPW: EditText
    private lateinit var tvForgetPw: TextView
    private lateinit var btnLogin: Button
    private lateinit var linkSignup: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        editEmail = findViewById(R.id.editEmail)
        editPW = findViewById(R.id.editPW)
        tvForgetPw = findViewById(R.id.tvForgetPw)
        btnLogin = findViewById(R.id.btnLogin)
        linkSignup = findViewById(R.id.linkSignup)

        btnLogin.setOnClickListener {
            val email = editEmail.text.toString().trim()
            val password = editPW.text.toString().trim()

            //CHECK: if empty show Toast
            if(email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter email & password!!", Toast.LENGTH_LONG).show()
            }else{
                if(email == "test@gmail.com" && password == "1234"){
                    Toast.makeText(this, "Login pass avec success", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)

                    editEmail.text.clear()
                    editPW.text.clear()
                    finish()
                }else{
                    Toast.makeText(this, "Invalide credentials", Toast.LENGTH_SHORT).show()
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