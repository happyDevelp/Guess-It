package com.example.guessit.screens.score

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.guessit.R
import com.example.guessit.databinding.ScoreFragmentBinding
import com.example.guessit.screens.game.GameFragmentDirections


class ScoreFragment : Fragment() {
    private lateinit var binding: ScoreFragmentBinding
    private lateinit var viewModel: ScoreViewModel
    private lateinit var viewModelFactory: ScoreViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = ScoreFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val scoreFragmentArgs by navArgs<ScoreFragmentArgs>()
        super.onViewCreated(view, savedInstanceState)
        viewModelFactory = ScoreViewModelFactory(ScoreFragmentArgs.fromBundle(requireArguments()).score)
        viewModel = ViewModelProvider(this, viewModelFactory) // passing second parameter I say to my ViewModelProvider to use this Fabric for create ScoreViewModel
            .get(ScoreViewModel::class.java)

        binding.playAgainButton.setOnClickListener {
            viewModel.onPlayAgain()
        }

        viewModel.score.observe(viewLifecycleOwner) {finalScore ->
            binding.scoreText.text = finalScore.toString()
        }

        viewModel.eventPlayAgain.observe(viewLifecycleOwner){currentState ->
            if (currentState){
                findNavController().navigate(ScoreFragmentDirections.actionRestart())
                viewModel.gameRestarted()
            }
        }

    }



}