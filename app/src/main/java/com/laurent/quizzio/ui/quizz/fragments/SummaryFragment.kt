package com.laurent.quizzio.ui.quizz.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.laurent.quizzio.R
import com.laurent.quizzio.ui.quizz.viewmodel.QuizzViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.summary_fragment.view.*

@AndroidEntryPoint
class SummaryFragment : Fragment() {

    private val quizzViewModel : QuizzViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewSummary : View = inflater.inflate(R.layout.summary_fragment, container, false)

        viewSummary.tvCorrectAnswers.text = String.format(getString(R.string.correct_answers), "${quizzViewModel.correctAnswer}")

        viewSummary.btnRestart.setOnClickListener(View.OnClickListener {
            quizzViewModel.reloadQuizz()
        })
        return viewSummary
    }
}
