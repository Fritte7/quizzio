package com.laurent.quizzio.data.repositories

import com.laurent.quizzio.data.models.Question
import com.laurent.quizzio.data.models.Quizz
import com.laurent.quizzio.data.network.INetworkAPI
import javax.inject.Inject

class QuizzRepository @Inject constructor(
    private val api : INetworkAPI
) {

    suspend fun getQuizz() : Quizz {
        val quizz = api.getQuizz(1)
        quizz.questions = randomizeAndOnly10(quizz.questions)
        return quizz
    }

    private fun randomizeAndOnly10(questions: List<Question>) : List<Question> {
        val questionsRandomized = questions.shuffled().dropLast(10)
        questionsRandomized.forEach { it.choice.shuffled() }
        return questionsRandomized
    }
}