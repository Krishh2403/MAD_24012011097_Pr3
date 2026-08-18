package com.example.mad_24012011097_pr3

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        login()
    }
    fun login() {
        findViewById<Button>(R.id.btnLoginSubmit).setOnClickListener {
            val email = findViewById<EditText>(R.id.etEmail).text.toString()
            val password = findViewById<EditText>(R.id.etPassword).text.toString()
            Toast.makeText(this, "Welcome, $email", Toast.LENGTH_SHORT).show()
        }
        findViewById<TextView>(R.id.tvForgotPassword).setOnClickListener {
            Toast.makeText(this, "Forgot Password Clicked", Toast.LENGTH_SHORT).show()
        }
    }
}