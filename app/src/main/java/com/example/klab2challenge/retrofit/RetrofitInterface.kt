package com.example.klab2challenge.retrofit

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitInterface {

//    @GET("/")
//    fun SampleAPI(sampleRequest: SampleRequest) : Call<SampleResponse>

    //안해도 됨 demo는
    @POST("/challenge/setChallenge")
    fun setChallenge(
        @Body request: SetChallengeRequest
    ): Call<SetChallengeResponse>

    @POST("/challenge/getChallenge")
    fun getChallenge(
        @Body
        request: GetChallengeRequest
    ): Call<GetChallengeResponse>

    //완료
    @POST("/challenge/getPopularChallenges")
    fun getChallenge(
        @Body
        request: GetPopularChallengesRequest
    ): Call<GetPopularChallengesResponse>

    //완료
    @POST("/challenge/getOfficialOrUserChallenges")
    fun getChallenge(
        @Body
        request: GetOfficialOrUserChallengesRequest
    ): Call<GetOfficialOrUserChallengesResponse>

    //완료
    @POST("/challenge/getRelatedChallenges")
    fun getChallenge(
        @Body
        request: GetRelatedChallengesRequest
    ): Call<GetRelatedChallengesResponse>

    @POST("/comment/setComment")
    fun setComment(
        @Body
        request: SetCommentRequest
    ): Call<SetCommentResponse>

    @GET("/comment/getAllComments")
    fun getAllComments(
        @Query("proofPostId")
        proofPostId: Int
    ): Call<GetAllCommentsResponse>

    @POST("/memberChallenge/joinChallenge")
    fun setChallenge(
        @Body
        request: JoinChallengeRequest
    ): Call<JoinChallengeResponse>

    @POST("/proofPost/setProofPost")
    fun setProofPost(
        @Body
        request: SetProofPostRequest
    ): Call<SetProofPostResponse>

    @GET("/proofPost/getAllProofPosts")
    fun getProofPosts(
        @Query("challengeId") challengeId : Int
    ): Call<GetProofPostsResponse>

    @GET("/proofPost/getProofPost")
    fun getProofPost(
        @Query("proofPostId") proofPostId : Int
    ): Call<GetProofPostResponse>
}