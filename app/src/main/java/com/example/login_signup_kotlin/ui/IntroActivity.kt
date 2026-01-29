package com.example.login_signup_kotlin.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.login_signup_kotlin.R

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val prefs = getSharedPreferences("app_prefs", MODE_PRIVATE)
        if(prefs.getBoolean("is_logged_in", false)) {
            startActivity(Intent(this, MainActivity::class.java))
        }
        setContentView(R.layout.intro_activity)

        val btnGetStarted = findViewById<Button>(R.id.buttonGetStarted)
        btnGetStarted.setOnClickListener {
            val intent = Intent(this@IntroActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}