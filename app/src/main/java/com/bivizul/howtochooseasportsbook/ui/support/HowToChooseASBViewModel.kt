package com.bivizul.howtochooseasportsbook.ui.support

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bivizul.howtochooseasportsbook.data.Constant.UNCHEKED_CAST
import com.bivizul.howtochooseasportsbook.data.HowToChooseASBRepository
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import javax.inject.Inject

class HowToChooseASBViewModel @Inject constructor(howToChooseASBRepository: HowToChooseASBRepository) :
    ViewModel() {

    val howToChooseASB = howToChooseASBRepository.howToChooseASBFlow

}

class HowToChooseASBViewModelFactory @AssistedInject constructor(
    private val howToChooseASBRepository: HowToChooseASBRepository
    ) : ViewModelProvider.Factory {

        @Suppress(UNCHEKED_CAST)
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HowToChooseASBViewModel(howToChooseASBRepository) as T
        }

        @AssistedFactory
        interface Factory{
            fun create():HowToChooseASBViewModelFactory
        }

}