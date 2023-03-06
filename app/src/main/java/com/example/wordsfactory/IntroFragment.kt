package com.example.wordsfactory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class IntroFragment( private val position: Int ): Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val layout = when( position ) {
            0 -> R.layout.fragment_intro_first
            1 -> R.layout.fragment_intro_second
            else -> R.layout.fragment_intro_third
        }
        return inflater.inflate( layout, container, false )
    }
}