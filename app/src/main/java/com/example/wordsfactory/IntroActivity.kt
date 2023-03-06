package com.example.wordsfactory

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class IntroActivity: AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var adapter: IntroAdapter
    private lateinit var skipButton: Button
    private lateinit var nextButton: Button
    private lateinit var pageIndicator: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        supportActionBar?.hide()

        viewPager = findViewById( R.id.viewPager )
        skipButton = findViewById<View>( R.id.skipButton ) as Button
        nextButton = findViewById<View>( R.id.nextButton ) as Button
        pageIndicator = findViewById(R.id.pageIndicator)
        adapter = IntroAdapter( this )

        viewPager.adapter = adapter
        viewPager.registerOnPageChangeCallback( object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected( position: Int ) {
                if( position == 1 ) {
                    nextButton.text = resources.getString( R.string.next )
                }
                if( position == 2 ) {
                    nextButton.text = resources.getString( R.string.lets_start )
                }
                super.onPageSelected( position )
            }
        })

        TabLayoutMediator(pageIndicator, viewPager) { _, _ -> null }.attach()

        skipButton.setOnClickListener {
            startActivity( Intent( this, SignupActivity::class.java ) )
        }

        nextButton.setOnClickListener {
            when( viewPager.currentItem ) {
                0 -> {
                    viewPager.currentItem++
                }
                1 -> {
                    viewPager.currentItem++
                    nextButton.text = resources.getString( R.string.lets_start )
                }
                2 -> {
                    startActivity( Intent( this, SignupActivity::class.java ) )
                }
            }
        }
    }
}