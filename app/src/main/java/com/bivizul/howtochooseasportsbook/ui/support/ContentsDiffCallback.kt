package com.bivizul.howtochooseasportsbook.ui.support

import androidx.recyclerview.widget.DiffUtil
import com.bivizul.howtochooseasportsbook.data.model.Step

object ContentsDiffCallback : DiffUtil.ItemCallback<Step>() {

    override fun areItemsTheSame(oldItem: Step, newItem: Step): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Step, newItem: Step): Boolean {
        return oldItem == newItem
    }
}