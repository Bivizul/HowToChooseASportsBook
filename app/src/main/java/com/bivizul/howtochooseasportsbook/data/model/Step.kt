package com.bivizul.howtochooseasportsbook.data.model

import androidx.annotation.Keep

@Keep
data class Step(
    val id: Int,
    val subtitle: String,
    val title: String,
    var openClose:Boolean = true,
)