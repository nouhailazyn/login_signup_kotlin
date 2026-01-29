package com.example.login_signup_kotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.intro_activity)

        val btnGetStarted = findViewById<Button>(R.id.buttonGetStarted)
        btnGetStarted.setOnClickListener {
            val intent = Intent(this@IntroActivity, LoginActivity::class.java)
            startActivity(intent)
            //Optionally finish IntroActivity to prevent going back
            finish()
        }
    }
}