package com.bivizul.howtochooseasportsbook.ui.support

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bivizul.howtochooseasportsbook.data.model.Step
import com.bivizul.howtochooseasportsbook.databinding.ItemContentsBinding

class ContentsAdapter :
    ListAdapter<Step, ContentsAdapter.ContentsViewHolder>(ContentsDiffCallback) {

    class ContentsViewHolder(val binding: ItemContentsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentsViewHolder {

        val binding = ItemContentsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ContentsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContentsViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            textItemTitle.text = item.title
            textItemSubtitle.text = item.subtitle
            textItemSubtitle.visibility = if (item.openClose) View.VISIBLE else View.GONE

            root.setOnClickListener {
                item.openClose = !item.openClose
                notifyItemChanged(position)
            }
        }
    }
}