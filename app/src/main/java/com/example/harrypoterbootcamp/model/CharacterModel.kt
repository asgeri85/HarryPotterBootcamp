package com.example.harrypoterbootcamp.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterModel(
    val id:Int,
    val name:String,
    @Json(name = "image_url")
    val imageUrl:String,
    val house:String
):Parcelable
