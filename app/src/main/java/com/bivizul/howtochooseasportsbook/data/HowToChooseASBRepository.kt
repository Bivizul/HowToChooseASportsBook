package com.bivizul.howtochooseasportsbook.data

import android.util.Log
import com.bivizul.howtochooseasportsbook.data.model.HowToChooseASB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class HowToChooseASBRepository @Inject constructor(private val netService: NetService) {

    val howToChooseASBFlow = flow {
        emit(fromResToObj(netService.getHowToChooseASB()))
    }.flowOn(Dispatchers.IO)

    fun fromResToObj(response: Response<HowToChooseASB>):HowToChooseASB?{
        var howToChooseASB : HowToChooseASB? = null
        if (response.isSuccessful){
            response.body()?.let {
                howToChooseASB = it
            }
        }
        Log.e("qwer","response : $response")
        return howToChooseASB
    }

}