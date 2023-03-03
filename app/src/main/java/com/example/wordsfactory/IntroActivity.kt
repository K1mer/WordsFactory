package com.example.wordsfactory

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        supportActionBar?.hide()

        val skipButton = findViewById<View>( R.id.skipButton ) as Button
        skipButton.setOnClickListener {
            startActivity( Intent( this, SignupActivity::class.java ) )
        }
    }
}