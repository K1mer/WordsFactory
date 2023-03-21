package com.example.wordsfactory

import android.app.AlertDialog
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Group
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.wordsfactory.domain.Word
import com.example.wordsfactory.services.ApiHelper
import com.google.android.material.tabs.TabLayout
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class DictionaryActivity: AppCompatActivity() {
    private lateinit var tabLayout: TabLayout
    private lateinit var searchLayout: TextInputLayout
    private lateinit var hiddenGroup: Group
    private lateinit var searchEditText: EditText
    private lateinit var meaningsLayout: RecyclerView
    private lateinit var definitionAdapter: DefinitionAdapter

    private fun playPhoneticAudio( audioSrc: String ) {
        val mediaPlayer = MediaPlayer()
        try {
            mediaPlayer.setDataSource( audioSrc )
            mediaPlayer.prepare()
            mediaPlayer.start()
        } catch ( _: Throwable ) {}
    }

    private fun notFoundWordHandler() {
        AlertDialog.Builder( this@DictionaryActivity )
            .setTitle( android.R.string.dialog_alert_title )
            .setMessage( resources.getString( R.string.dialog_word_not_found ) )
            .setPositiveButton( resources.getString( android.R.string.ok ) ) {
                dialog, _ -> dialog.cancel()
            }
            .show()
    }

    private fun foundWordHandler( word: Word ) {
        hiddenGroup.isVisible = true

        findViewById<TextView>( R.id.foundWordText ).text = word.word
        findViewById<TextView>( R.id.pronounceText ).text = word.phoneticText
        findViewById<TextView>( R.id.posText ).text = word.partOfSpeech

        val listenButton = findViewById<ImageButton>( R.id.listenButton )
        if( !word.phoneticAudioSrc.isNullOrEmpty() ) {
            listenButton.isVisible = true
            listenButton.setOnClickListener {
                playPhoneticAudio( word.phoneticAudioSrc!! )
            }
        } else {
            listenButton.isVisible = false
        }

        meaningsLayout.adapter = DefinitionAdapter( this, word.definitions ?: arrayListOf() )
    }

    override fun onCreate( savedInstanceState: Bundle? ) {
        super.onCreate( savedInstanceState )
        setContentView( R.layout.activity_dictionary )
        supportActionBar?.hide()

        tabLayout = findViewById( R.id.tabLayout )
        searchLayout = findViewById( R.id.textSearchLayout )
        searchEditText = findViewById( R.id.searchField )
        meaningsLayout = findViewById( R.id.meaningsLayout )
        hiddenGroup = findViewById( R.id.hidableGroup )

        definitionAdapter = DefinitionAdapter( this, arrayListOf() )
        meaningsLayout.adapter = definitionAdapter

        hiddenGroup.isVisible = false

        tabLayout = findViewById( R.id.tabLayout )
        tabLayout.addOnTabSelectedListener( object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected( tab: TabLayout.Tab? ) {
                when( tab?.position ) {
                    1 -> startActivity( Intent( this@DictionaryActivity, TrainingActivity::class.java ) )
                    2 -> startActivity( Intent( this@DictionaryActivity, VideoActivity::class.java ) )
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        searchLayout.setEndIconOnClickListener {
            lifecycleScope.launch( Dispatchers.IO ) {
                val searchText = searchEditText.text.toString()

                if( !searchText.isNullOrEmpty() ) {
                    val wordInfo = ApiHelper.getWordInfo( searchText )

                    withContext( Dispatchers.Main ) {
                        if( wordInfo == null ) {
                            notFoundWordHandler()
                        } else {
                            foundWordHandler( wordInfo )
                        }
                    }
                }
            }
        }
    }
}