package com.laurent.quizzio.ui.quizz.fragments

import android.animation.Animator
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.laurent.quizzio.R
import com.laurent.quizzio.ui.quizz.viewmodel.QuizzViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.splash_fragment.view.*

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private val quizzViewModel : QuizzViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewSplash = inflater.inflate(R.layout.splash_fragment, container, false)

        viewSplash.animationViewStar.addAnimatorListener(object : Animator.AnimatorListener{
            override fun onAnimationEnd(animation: Animator?) {
                viewSplash.btnStart.visibility = View.VISIBLE

                viewSplash.btnStart.setOnClickListener(
                    View.OnClickListener { quizzViewModel.startTheGame() }
                )
            }

            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
            }
        })

        Handler(Looper.getMainLooper())
            .postDelayed({

                viewSplash.animationViewStar.visibility = View.VISIBLE
                viewSplash.animationViewStar.playAnimation()
        }, 2700)

        Handler()
            .postDelayed({

                viewSplash.textTitle1.visibility = View.VISIBLE
                viewSplash.textTitle1.startAnimation(AnimationUtils.loadAnimation(context, R.anim.zoom_in))

                Handler()
                    .postDelayed({

                        viewSplash.textTitle2.visibility = View.VISIBLE
                        viewSplash.textTitle2.startAnimation(AnimationUtils.loadAnimation(context, R.anim.zoom_in))
                    }, 750)
            }, 3500)
        return viewSplash
    }
}