package com.laurent.quizzio.ui.quizz.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.laurent.quizzio.R
import com.laurent.quizzio.data.models.Question
import com.laurent.quizzio.data.utils.Status
import com.laurent.quizzio.ui.quizz.fragments.adapters.QuizzCardAdapter
import com.laurent.quizzio.ui.quizz.viewmodel.QuizzViewModel
import com.yuyakaido.android.cardstackview.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.quizz_fragment.*

@AndroidEntryPoint
class QuizzFragment : Fragment(), CardStackListener, View.OnClickListener {

    private val quizzViewModel : QuizzViewModel by activityViewModels()

    private lateinit var viewQuizz : View
    private lateinit var cardStackLM : CardStackLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewQuizz = inflater.inflate(R.layout.quizz_fragment, container, false)

        initCardView()

        quizzViewModel.quizz.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    cardStackView.adapter = QuizzCardAdapter(it.data!!.questions, this)
                }
                Status.LOADING -> {
                    //todo
                }
                Status.ERROR -> {
                    //todo
                }
            }
        })

        return viewQuizz
    }

    private fun initCardView() {
        val cardStackView = viewQuizz.findViewById<CardStackView>(R.id.cardStackView)

        cardStackLM = CardStackLayoutManager(context, this)
        cardStackLM.setStackFrom(StackFrom.TopAndRight)
        cardStackLM.setVisibleCount(3)
        cardStackLM.setTranslationInterval(8.0f)
        cardStackLM.setScaleInterval(0.95f)

        cardStackLM.setSwipeableMethod(SwipeableMethod.Automatic)
        /* normal speed */
        val setting = SwipeAnimationSetting.Builder()
            .setDirection(Direction.Right)
            .setDirection(Direction.Left)
            .setDuration(Duration.Normal.duration)
            .setInterpolator(AccelerateInterpolator())
            .build()
        cardStackLM.setSwipeAnimationSetting(setting)

        cardStackView.layoutManager = cardStackLM
        cardStackView.adapter = QuizzCardAdapter(emptyList(), this)
    }

    /******************** Interfaces **************************/

    override fun onClick(v: View?) {
        val itemView = v!!.parent.parent as View
        val question : Question = itemView.tag as Question
        when (v.id) {
            R.id.tvChoice1 -> {
                if (quizzViewModel.isCorrectAnswer(question,0, question.answer)) {
                    correctAnswer()
                } else {
                    wrongAnswer()
                }
            }
            R.id.tvChoice2 -> {
                if (quizzViewModel.isCorrectAnswer(question,1, question.answer)) {
                    correctAnswer()
                } else {
                    wrongAnswer()
                }
            }
            R.id.tvChoice3 -> {
                if (quizzViewModel.isCorrectAnswer(question,2, question.answer)) {
                    correctAnswer()
                } else {
                    wrongAnswer()
                }
            }
            R.id.tvChoice4 -> {
                if (quizzViewModel.isCorrectAnswer(question,3, question.answer)) {
                    correctAnswer()
                } else {
                    wrongAnswer()
                }
            }
        }
    }

    private fun wrongAnswer() {
        val setting = SwipeAnimationSetting.Builder()
            .setDirection(Direction.Left)
            .setDuration(Duration.Normal.duration)
            .setInterpolator(AccelerateInterpolator())
            .build()
        cardStackLM.setSwipeAnimationSetting(setting)
        cardStackView.swipe()
    }

    private fun correctAnswer() {
        val setting = SwipeAnimationSetting.Builder()
            .setDirection(Direction.Right)
            .setDuration(Duration.Normal.duration)
            .setInterpolator(AccelerateInterpolator())
            .build()
        cardStackLM.setSwipeAnimationSetting(setting)
        cardStackView.swipe()
    }

    override fun onCardSwiped(direction: Direction?) {
        if (cardStackLM.getTopPosition() == cardStackView.adapter!!.getItemCount()) {
            quizzViewModel.endTheGame()
        }
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {
    }

    override fun onCardRewound() {
    }

    override fun onCardCanceled() {
    }

    override fun onCardAppeared(view: View?, position: Int) {
    }

    override fun onCardDisappeared(view: View?, position: Int) {
    }
}