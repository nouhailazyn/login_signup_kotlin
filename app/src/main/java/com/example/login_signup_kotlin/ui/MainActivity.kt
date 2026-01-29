package com.example.login_signup_kotlin.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.login_signup_kotlin.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editLibelle = findViewById<EditText>(R.id.editLibelle)
        val editPrix = findViewById<EditText>(R.id.editPrix)
        val editQuantite = findViewById<EditText>(R.id.editQuantite)
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnView = findViewById<Button>(R.id.btnView)

        btnAdd.setOnClickListener {
            Toast.makeText(this@MainActivity, "Button Add clicked", Toast.LENGTH_SHORT).show()
        }
    }
}