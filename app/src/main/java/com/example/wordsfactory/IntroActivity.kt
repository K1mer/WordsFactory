package com.example.wordsfactory

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.wordsfactory.databinding.ActivityIntroBinding
import com.google.android.material.tabs.TabLayoutMediator

class IntroActivity: AppCompatActivity() {
    private lateinit var binding: ActivityIntroBinding

    override fun onCreate( savedInstanceState: Bundle? ) {
        super.onCreate( savedInstanceState )
        supportActionBar?.hide()

        binding = ActivityIntroBinding.inflate( layoutInflater )
        setContentView( binding.root )

        binding.viewPager.adapter = IntroAdapter( this )
        binding.viewPager.registerOnPageChangeCallback( object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected( position: Int ) {
                if( position == 1 ) {
                    binding.nextButton.text = resources.getString( R.string.next )
                }
                if( position == 2 ) {
                    binding.nextButton.text = resources.getString( R.string.lets_start )
                }
                super.onPageSelected( position )
            }
        })

        TabLayoutMediator( binding.pageIndicator, binding.viewPager ) { _, _ -> }.attach()

        binding.skipButton.setOnClickListener {
            startActivity( Intent( this, SignupActivity::class.java ) )
        }

        binding.nextButton.setOnClickListener {
            when( binding.viewPager.currentItem ) {
                0 -> {
                    binding.viewPager.currentItem++
                }
                1 -> {
                    binding.viewPager.currentItem++
                    binding.nextButton.text = resources.getString( R.string.lets_start )
                }
                2 -> {
                    startActivity( Intent( this, SignupActivity::class.java ) )
                }
            }
        }
    }
}