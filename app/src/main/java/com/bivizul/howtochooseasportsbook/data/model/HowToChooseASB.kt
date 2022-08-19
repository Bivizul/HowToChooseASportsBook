package com.bivizul.howtochooseasportsbook.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class HowToChooseASB(
    @SerializedName("howToChooseASportsBook")
    val howToChooseASB: Contents
)