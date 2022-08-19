package com.bivizul.howtochooseasportsbook.data

import com.bivizul.howtochooseasportsbook.data.model.GetChoose
import com.bivizul.howtochooseasportsbook.data.model.HowToChooseASB
import com.bivizul.howtochooseasportsbook.data.model.SetChoose
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface NetService {

    @GET("23HowToChooseASportsBook/howToChooseASportsBook.json")
    suspend fun getHowToChooseASB(): Response<HowToChooseASB>

    @POST("23HowToChooseASportsBook/choose.php")
    suspend fun getChoose(@Body setChoose: SetChoose): Response<GetChoose>

}