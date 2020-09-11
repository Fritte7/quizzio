package com.laurent.quizzio.ui.quizz.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.laurent.quizzio.data.models.Question
import com.laurent.quizzio.data.models.Quizz
import com.laurent.quizzio.data.repositories.QuizzRepository
import com.laurent.quizzio.data.utils.Resource
import kotlinx.coroutines.Dispatchers

class QuizzViewModel @ViewModelInject constructor(
    private val repository: QuizzRepository
) : ViewModel() {

    var gameStart : MutableLiveData<Boolean> = MutableLiveData()
    var gameEnd : MutableLiveData<Boolean> = MutableLiveData()
    lateinit var quizz : LiveData<Resource<Quizz>>
    var correctAnswer : Int = 0
    val life50 : Boolean = false
    val life15 : Boolean = false

    fun getQuizz() {
        quizz = liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(Resource.success(data = repository.getQuizz()))

            } catch (exception : Exception) {
                Log.e("Test", "Error= ${exception.message}")
                emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
            }
        }
    }

    fun isCorrectAnswer(question: Question, positionClicked: Int, answer: String) : Boolean {
        val result = question.choice[positionClicked] == answer
        if (result) {
            correctAnswer++
        }
        return result
    }

    fun startTheGame() {
        gameStart.value = true
        gameEnd.value = false
    }

    fun endTheGame() {
        gameStart.value = false
        gameEnd.value = true
    }

    fun reloadQuizz() {
        getQuizz()
        startTheGame()
    }
}