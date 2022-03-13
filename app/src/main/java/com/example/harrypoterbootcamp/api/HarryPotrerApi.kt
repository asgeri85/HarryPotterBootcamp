package com.example.harrypoterbootcamp.api

import com.example.harrypoterbootcamp.model.ResponseModel
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface HarryPotrerApi {

    @GET("all_data.php")
    suspend fun getAllCharacter():ResponseModel

    @POST("filter_data.php")
    @FormUrlEncoded
    suspend fun getFilterData(@Field("filter") filter:String):ResponseModel
}