package com.example.wordsfactory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class IntroFirstFragment : Fragment() {
    // Смотри здесь https://www.youtube.com/watch?v=D7D7MpuQD7Y&list=PLyfVjOYzujuj20Y-3kVhT3Zro9CrMNgNS&index=18
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_intro_first, container, false)
    }

}