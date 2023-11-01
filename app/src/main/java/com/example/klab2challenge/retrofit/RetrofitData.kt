package com.example.klab2challenge.retrofit

import com.google.gson.annotations.SerializedName

data class SampleRequest(
    @SerializedName("id") val id : Int
)
data class SampleResponse(
    @SerializedName("id") val id : Int
)