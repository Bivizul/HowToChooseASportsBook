package com.bivizul.howtochooseasportsbook.data

import com.bivizul.howtochooseasportsbook.data.Constant.ERROR_MESSAGE
import com.bivizul.howtochooseasportsbook.data.model.GetChoose
import com.bivizul.howtochooseasportsbook.data.model.SetChoose
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

class ChooseRepository @Inject constructor(private val netService: NetService) {

    private val _choose = MutableSharedFlow<GetChoose>()
    val choose: SharedFlow<GetChoose> = _choose.asSharedFlow()

    suspend fun getChoose(setChoose: SetChoose) {
        val response = netService.getChoose(setChoose)
        if (response.isSuccessful) {
            response.body()?.let {
                _choose.emit(it)
            }
        } else {
            _choose.emit(GetChoose(ERROR_MESSAGE))
        }
    }
}