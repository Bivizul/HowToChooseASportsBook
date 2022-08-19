package com.bivizul.howtochooseasportsbook.data.model

import androidx.annotation.Keep

@Keep
data class Tip(
    val idTips: Int,
    val step: List<Step>,
    val subtitleTips: String,
    val titleTips: String,
)