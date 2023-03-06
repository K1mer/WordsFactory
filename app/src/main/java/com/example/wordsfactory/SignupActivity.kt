package com.example.wordsfactory

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SignupActivity : AppCompatActivity() {
    private lateinit var prevButton: FloatingActionButton
    private lateinit var loginButton: Button
    private lateinit var signupButton: Button
    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        supportActionBar?.hide()

        prevButton = findViewById( R.id.prevButton )
        loginButton = findViewById( R.id.loginButton )
        signupButton = findViewById( R.id.signupButton )
        nameEditText = findViewById( R.id.nameEditText )
        emailEditText = findViewById( R.id.emailEditText )
        passwordEditText = findViewById( R.id.passwordEditText )

        prevButton.setOnClickListener {
            startActivity( Intent( this, IntroActivity::class.java ) )
        }

        loginButton.setOnClickListener {
            startActivity( Intent( this, DictionaryActivity::class.java ) )
        }

        signupButton.setOnClickListener {
            if( nameEditText.text.isNullOrEmpty()
                || emailEditText.text.isNullOrEmpty()
                || passwordEditText.text.isNullOrEmpty() ) {

                AlertDialog.Builder( this )
                    .setTitle( android.R.string.dialog_alert_title )
                    .setMessage( resources.getString( R.string.toast_empty_signup_field ) )
                    .setPositiveButton( resources.getString( android.R.string.ok ) ) {
                        dialog, _ -> dialog.cancel()
                    }
                    .show()
            } else {
                startActivity( Intent( this, DictionaryActivity::class.java ) )
            }
        }
    }
}