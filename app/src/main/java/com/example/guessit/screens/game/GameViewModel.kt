package com.example.guessit.screens.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    // The current word
    private val _word = MutableLiveData<String>()
    val word: LiveData<String>
        get() = _word

    // The current score, can use just inside of this class
    private val _score = MutableLiveData<Int>()
    // This variable has override getter and setter so that I can just receive value and can`t to change
    val score: LiveData<Int>
        get() = _score


    // The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>

    init {
        Log.i("GameViewModel", "GameViewModel created!")
        resetList()
        nextWord()
        _score.value = 0
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

    fun nextWord() {
        if (wordList.isEmpty()) {
            /*gameFinished()*/
        } else {
            _word.value = wordList.removeAt(0)
        }
    }

    fun onSkip() {
        if (score.value!! > 0) //fix !!
            _score.value = score.value?.minus(1) //try to use another way.  UPD: tried, not worked, so should use just like here
        nextWord()
    }

    fun onCorrect() {
        _score.value = score.value?.plus(1)
        nextWord()
    }

}