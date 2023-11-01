package com.example.klab2challenge.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitInterface {

    @GET("/")
    fun SampleAPI(sampleRequest: SampleRequest) : Call<SampleResponse>

}