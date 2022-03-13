package com.example.harrypoterbootcamp.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URl = "https://canerture.com/api/harrypotterapp/"

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val retrofit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URl).build()

object HarryPotterRetrofit {
    val retrofitService: HarryPotrerApi by lazy { retrofit.create(HarryPotrerApi::class.java) }
}

