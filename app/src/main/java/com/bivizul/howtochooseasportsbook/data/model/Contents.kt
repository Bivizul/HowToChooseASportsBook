package com.bivizul.howtochooseasportsbook.data.model

import androidx.annotation.Keep

@Keep
data class Contents(
    val intro: String,
    val tips: List<Tip>
)