package com.psp.collegeforum.Retrofit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.psp.collegeforum.databinding.ItemQuestionBinding
import com.psp.collegeforum.ui.MainFragment

class QuestionAdapter :RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>(){
    inner class QuestionViewHolder(val binding:ItemQuestionBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Question>(){
        override fun areItemsTheSame(oldItem: Question, newItem: Question): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Question, newItem: Question): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this,diffCallback)
    var question : List<Question>
     get()=differ.currentList
     set(value) {differ.submitList(value)}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        return QuestionViewHolder(ItemQuestionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.binding.apply {
            val question = question[position]
            etQuestionInMRV.text = question.title
            tvAnswerInMRV.text = question.title
            tvUserNameInMRV.text = question.title
            tvTimeInMRV.text = question.id.toString()
        }
    }

    override fun getItemCount() = question.size

}