package com.example.wordsfactory

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.google.android.material.tabs.TabLayout

class VideoActivity: AppCompatActivity() {
    private lateinit var tabLayout: TabLayout
    private lateinit var webView: WebView
    private val hardcodedLink = "https://learnenglish.britishcouncil.org/general-english/video-zone"
    @SuppressLint( "SetJavaScriptEnabled" )
    override fun onCreate( savedInstanceState: Bundle? ) {
        super.onCreate( savedInstanceState )
        setContentView( R.layout.activity_video )
        supportActionBar?.hide()

        webView = findViewById( R.id.webView )
        tabLayout = findViewById( R.id.tabLayout )

        tabLayout.getTabAt( 2 )?.select()

        tabLayout.addOnTabSelectedListener( object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected( tab: TabLayout.Tab? ) {
                when( tab?.position ) {
                    0 -> startActivity( Intent( this@VideoActivity, DictionaryActivity::class.java ) )
                    1 -> startActivity( Intent( this@VideoActivity, TrainingActivity::class.java ) )
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        val webClient = object: WebViewClient() {
            @TargetApi(Build.VERSION_CODES.N)
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                if( !( request?.url.toString().startsWith( hardcodedLink ) ) ) {
                    return true
                }

                return super.shouldOverrideUrlLoading(view, request)
            }
        }

        webView.webViewClient = webClient
        webView.settings.javaScriptEnabled = true
        webView.loadUrl( hardcodedLink )
    }
}