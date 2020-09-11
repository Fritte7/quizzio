package com.laurent.quizzio.ui.quizz

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.laurent.quizzio.R
import com.laurent.quizzio.ui.quizz.fragments.QuizzFragment
import com.laurent.quizzio.ui.quizz.fragments.SplashFragment
import com.laurent.quizzio.ui.quizz.fragments.SummaryFragment
import com.laurent.quizzio.ui.quizz.viewmodel.QuizzViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizzActivity : AppCompatActivity(){

    private val quizzViewModel : QuizzViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quizz)

        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, SplashFragment())
            .commitNow()

        quizzViewModel.getQuizz()
        quizzViewModel.gameStart.observe(this, Observer {
            when (it) {
                true -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, QuizzFragment())
                        .commitNow()
                }
            }
        })

        quizzViewModel.gameEnd.observe(this, Observer {
            when (it) {
                true -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, SummaryFragment())
                        .commitNow()
                }
            }
        })
    }
}