package com.example.guessit.screens.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.guessit.R
import com.example.guessit.databinding.GameFragmentBinding

class GameFragment : Fragment() {
    private lateinit var binding: GameFragmentBinding
    var score: Int = 0
    var word: String = ""
    private var wordList = mutableListOf<String>(
        "queen",
        "hospital",
        "basketball",
        "cat",
        "change",
        "snail",
        "soup",
        "calendar",
        "sad",
        "desk",
        "guitar",
        "home",
        "railway",
        "zebra",
        "jelly",
        "car",
        "crow",
        "trade",
        "bag",
        "roll",
        "bubble"
    )

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
        resetList()
        nextWord()


        binding.correctButton.setOnClickListener { onCorrect() }

        binding.skipButton.setOnClickListener { onSkip() }

        updateScoreText()
        updateWordText()

    }


    fun resetList() {
        wordList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble"
        )
        wordList.shuffle()
    }

    private fun gameFinished(){
        val action =GameFragmentDirections.actionGameToScore(score)
        findNavController().navigate(action)
    }

    private fun nextWord(){
        if(wordList.isEmpty()){
            gameFinished()
        }
        else{
            wordList.removeAt(0)
        }
        updateWordText()
        updateScoreText()
    }

    private fun updateWordText() {
        binding.wordText.text = word.toString()
    }

    private fun updateScoreText() {
        binding.scoreText.text = score.toString()
    }

    fun onSkip(){
        if(score > 0)
            score--
        nextWord()
    }

    private fun onCorrect(){
        score++
        nextWord()
    }
}