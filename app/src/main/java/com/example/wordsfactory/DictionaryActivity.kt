package com.example.wordsfactory

import android.app.AlertDialog
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Group
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import androidx.lifecycle.lifecycleScope
import com.example.wordsfactory.domain.Word
import com.example.wordsfactory.services.ApiHelper
import com.google.android.material.tabs.TabLayout
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*


class DictionaryActivity: AppCompatActivity() {
    private lateinit var tabLayout: TabLayout
    private lateinit var searchLayout: TextInputLayout
    private lateinit var showableGroup: Group
    private lateinit var searchEditText: EditText
    private lateinit var meaningsLayout: LinearLayout

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
        showableGroup.isVisible = true

        meaningsLayout.removeAllViewsInLayout()

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

        word.definitions?.forEach { definition ->
            val meaningLayout = ConstraintLayout( ContextThemeWrapper( this, R.style.Widget_WordsFactory_Dictionary_Meaning_Background ) )
            val meaningText = TextView( this, null, 0, R.style.Widget_WordsFactory_Dictionary_Meaning_Description )

            meaningText.text = definition.definition
            meaningText.id = View.generateViewId()

            meaningLayout.addView( meaningText )

            if( !definition.example.isNullOrEmpty() ) {
                val exampleLayout = ConstraintLayout( ContextThemeWrapper( this, R.style.Widget_WordsFactory_Dictionary_Meaning_ExampleLayout ) )
                val exampleLabel = TextView( this, null, 0, R.style.Widget_WordsFactory_Dictionary_Meaning_ExampleLabel )
                val exampleText = TextView( this, null, 0, R.style.Widget_WordsFactory_Dictionary_Meaning_ExampleText )

                exampleText.text = definition.example
                exampleLabel.id = View.generateViewId()

                exampleLayout.addView( exampleLabel )
                exampleLayout.addView( exampleText )

                exampleText.updateLayoutParams<ConstraintLayout.LayoutParams> {
                    startToEnd = exampleLabel.id
                }

                meaningLayout.addView( exampleLayout )

                exampleLayout.updateLayoutParams<ConstraintLayout.LayoutParams> {
                    topToBottom = meaningText.id
                }
            }
            meaningsLayout.addView( meaningLayout )
        }
    }

    override fun onCreate( savedInstanceState: Bundle? ) {
        super.onCreate( savedInstanceState )
        setContentView( R.layout.activity_dictionary )
        supportActionBar?.hide()

        tabLayout = findViewById( R.id.tabLayout )
        searchLayout = findViewById( R.id.textSearchLayout )
        searchEditText = findViewById( R.id.searchField )
        meaningsLayout = findViewById( R.id.meaningsLayout )
        showableGroup = findViewById( R.id.hidableGroup )

        showableGroup.isVisible = false

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