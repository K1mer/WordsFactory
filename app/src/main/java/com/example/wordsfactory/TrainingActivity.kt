package com.example.wordsfactory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.tabs.TabLayout

class TrainingActivity : AppCompatActivity() {
    private lateinit var tabLayout: TabLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_training)
        supportActionBar?.hide()

        tabLayout = findViewById<View>( R.id.tabLayout ) as TabLayout

        tabLayout.getTabAt(1)?.select()

        tabLayout.addOnTabSelectedListener( object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected( tab: TabLayout.Tab? ) {
                when( tab?.position ) {
                    0 -> startActivity( Intent( this@TrainingActivity, DictionaryActivity::class.java ) )
                    2 -> startActivity( Intent( this@TrainingActivity, VideoActivity::class.java ) )
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

    }
}