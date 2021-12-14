package com.furybase.model

import com.google.gson.annotations.SerializedName

data class CountryModel (
    @SerializedName("c_type")
    val cType: Int,
    @SerializedName("countryCode")
    val countryCode: String,
    @SerializedName("countryId")
    val countryId: Int,
    @SerializedName("countryName")
    val countryName: String,
    @SerializedName("flag")
    val flag: String

)