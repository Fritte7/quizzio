package com.laurent.quizzio.ui.quizz.fragments.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.laurent.quizzio.R
import com.laurent.quizzio.data.models.Question

class QuizzCardAdapter(
    private var questions: List<Question>,
    private var onClick: View.OnClickListener
    ) : RecyclerView.Adapter<QuizzCardAdapter.QuizzHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): QuizzHolder {

        return QuizzHolder(LayoutInflater.from(parent.context)
                                .inflate(R.layout.item_question, parent, false))
    }

    override fun onBindViewHolder(holder: QuizzHolder, position: Int) {
        holder.bind(questions.get(position))
        holder.itemView.tag = questions.get(position)
        holder.tvChoice1?.setOnClickListener(onClick)
        holder.tvChoice2?.setOnClickListener(onClick)
        holder.tvChoice3?.setOnClickListener(onClick)
        holder.tvChoice4?.setOnClickListener(onClick)
    }

    override fun getItemCount(): Int {
        return questions.size
    }

    class QuizzHolder(v: View) : RecyclerView.ViewHolder(v) {
        var view: View = v
        var imgUrl: ImageView? = null
        var tvQuestion: TextView? = null
        var tvChoice1: TextView? = null
        var tvChoice2: TextView? = null
        var tvChoice3: TextView? = null
        var tvChoice4: TextView? = null

        init {
            imgUrl = view.findViewById(R.id.imgUrl)
            tvQuestion = view.findViewById(R.id.tvQuestion)
            tvChoice1 = view.findViewById(R.id.tvChoice1)
            tvChoice2 = view.findViewById(R.id.tvChoice2)
            tvChoice3 = view.findViewById(R.id.tvChoice3)
            tvChoice4 = view.findViewById(R.id.tvChoice4)
        }

        fun bind(question: Question) {
            Glide.with(view.context).load(question.url).into(imgUrl!!)
            tvQuestion?.text = question.question
            tvChoice1?.text = question.choice[0]
            tvChoice2?.text = question.choice[1]
            tvChoice3?.text = question.choice[2]
            tvChoice4?.text = question.choice[3]
        }
    }
}