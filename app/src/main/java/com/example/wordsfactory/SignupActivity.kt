package com.example.wordsfactory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SignupActivity : AppCompatActivity() {
    private lateinit var loginButton: Button
    private lateinit var signupButton: Button
    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        supportActionBar?.hide()

        loginButton = findViewById<View>( R.id.loginButton ) as Button
        signupButton = findViewById<View>( R.id.signupButton ) as Button

        nameEditText = findViewById<View>( R.id.nameEditText ) as EditText
        emailEditText = findViewById<View>( R.id.emailEditText ) as EditText
        passwordEditText = findViewById<View>( R.id.passwordEditText ) as EditText

        signupButton.setOnClickListener {
            if( nameEditText.text.isNullOrEmpty()
                || emailEditText.text.isNullOrEmpty()
                || passwordEditText.text.isNullOrEmpty() ) {
                Toast.makeText( this,
                                resources.getString( R.string.toast_empty_signup_field ),
                                Toast.LENGTH_LONG ).show()
            } else {
                startActivity( Intent( this, DictionaryActivity::class.java ) )
            }
        }

        loginButton.setOnClickListener {
            startActivity( Intent( this, DictionaryActivity::class.java ) )
        }
    }
}