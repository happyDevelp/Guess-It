package com.example.guessit.screens.game

import android.os.Bundle
import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.guessit.R
import com.example.guessit.databinding.GameFragmentBinding
import androidx.lifecycle.ViewModelProvider

/*const val SCORE = "score"*/

class GameFragment : Fragment() {
    private lateinit var viewModel: GameViewModel
    private lateinit var binding: GameFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = GameFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*  if (savedInstanceState != null) {
              score = savedInstanceState.getInt(SCORE)
          }*/

        Log.i("GameFragment", "Called ViewModelProvider")
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)


        binding.correctButton.setOnClickListener {
            viewModel.onCorrect()
        }

        binding.skipButton.setOnClickListener {
            viewModel.onSkip()
        }

        viewModel.score.observe(viewLifecycleOwner) { newScore ->
            binding.scoreText.text = newScore.toString()
        }

        viewModel.word.observe(viewLifecycleOwner) { newWord ->
            binding.wordText.text = newWord
        }

        viewModel.eventGameFinish.observe(viewLifecycleOwner) { hasFinished ->
            if (hasFinished) {
                gameFinished()
                viewModel.onGameFinishComplete()
            }
        }

        viewModel.currentTime.observe(viewLifecycleOwner) {newCurrentTime ->
            binding.timerText.text = DateUtils.formatElapsedTime(newCurrentTime)
        }

    }


    fun gameFinished() {
        val action = GameFragmentDirections.actionGameToScore(viewModel.score.value ?: 0)
        findNavController().navigate(action)

      /*  Toast.makeText(this.activity, "Game has finished", Toast.LENGTH_SHORT).show()*/
    }


    /*    override fun onSaveInstanceState(outState: Bundle) {
            super.onSaveInstanceState(outState)
           *//* Log.i("GameFragment", "OnSaveInstance is called")*//*
        outState.putInt(SCORE, score)
    }*/
}
