package com.bivizul.howtochooseasportsbook.ui.support

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.bivizul.howtochooseasportsbook.data.ChooseRepository
import com.bivizul.howtochooseasportsbook.data.Constant.UNCHEKED_CAST
import com.bivizul.howtochooseasportsbook.data.model.SetChoose
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch
import javax.inject.Inject

class WobataViewModel @Inject constructor(
    private val setChoose: SetChoose,
    private val chooseRepository: ChooseRepository,
) :
    ViewModel() {

    val choose = chooseRepository.choose

    fun setChoose() {
        viewModelScope.launch {
            chooseRepository.getChoose(setChoose)
        }
    }

}

class WobataViewModelFactory @AssistedInject constructor(
    @Assisted("setChoose") private val setChoose: SetChoose,
    private val chooseRepository: ChooseRepository,
) : ViewModelProvider.Factory {

    @Suppress(UNCHEKED_CAST)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WobataViewModel(setChoose, chooseRepository) as T
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("setChoose") setChoose: SetChoose,
        ): WobataViewModelFactory
    }
}