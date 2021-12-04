package com.example.wordapp.result

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import com.example.wordapp.model.Meaning
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.wordapp.R
import org.w3c.dom.Text

class WordResultAdapter: ListAdapter<Meaning, WordResultAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WordResultAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.meaning_view_holder, parent, false)
        )
    }

    override fun onBindViewHolder(holder: WordResultAdapter.ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val meaningResult: TextView = itemView.findViewById(R.id.tvMeaning)

        fun bind(item: Meaning) {
            meaningResult.text = item.meaning
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Meaning>() {
        override fun areItemsTheSame(oldItem: Meaning, newItem: Meaning): Boolean {
            return oldItem.meaning == newItem.meaning
        }

        override fun areContentsTheSame(oldItem: Meaning, newItem: Meaning): Boolean {
            return oldItem == newItem
        }
    }
}