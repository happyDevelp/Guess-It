package com.example.guessit.screens.title

import android.icu.text.CaseMap.Title
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.guessit.R
import com.example.guessit.databinding.TitleFragmentBinding

class TitleFragment : Fragment() {
    private lateinit var binding: TitleFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = TitleFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.playGameButton.setOnClickListener {//R.id.action_titleFragment_to_gameFragment
            findNavController().navigate(TitleFragmentDirections.actionTitleToGame())
        }

    }
}


