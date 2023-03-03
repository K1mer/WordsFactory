package com.example.wordsfactory

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()
        Handler(Looper.myLooper()!!).postDelayed({
            startActivity( Intent( this, IntroActivity::class.java ) )
            finish()
        }, 2000 )
    }
}