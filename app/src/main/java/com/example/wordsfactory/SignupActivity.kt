package com.example.wordsfactory

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wordsfactory.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    override fun onCreate( savedInstanceState: Bundle? ) {
        super.onCreate( savedInstanceState )
        supportActionBar?.hide()

        binding = ActivitySignupBinding.inflate( layoutInflater )
        setContentView( binding.root )

        binding.prevButton.setOnClickListener {
            startActivity( Intent( this, IntroActivity::class.java ) )
        }

        binding.loginButton.setOnClickListener {
            startActivity( Intent( this, DictionaryActivity::class.java ) )
        }

        binding.signupButton.setOnClickListener {
            if( binding.nameEditText.text.isNullOrEmpty()
                || binding.emailEditText.text.isNullOrEmpty()
                || binding.passwordEditText.text.isNullOrEmpty() ) {

                AlertDialog.Builder( this )
                    .setTitle( android.R.string.dialog_alert_title )
                    .setMessage( resources.getString( R.string.dialog_empty_signup_field ) )
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