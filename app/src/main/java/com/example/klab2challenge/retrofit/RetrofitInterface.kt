package com.example.klab2challenge.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitInterface {

//    @GET("/")
//    fun SampleAPI(sampleRequest: SampleRequest) : Call<SampleResponse>

    @POST("/challenge/setChallenge")
    fun setChallenge(request: SetChallengeRequest) : Call<SetChallengeResponse>

    @POST("/challenge/getChallenge")
    fun getChallenge(request: GetChallengeRequest) : Call<GetChallengeResponse>

    @POST("/challenge/getPopularChallenges")
    fun getChallenge(request: GetPopularChallengesRequest) : Call<GetPopularChallengesResponse>

    @POST("/challenge/getOfficialOrUserChallenges")
    fun getChallenge(request: GetOfficialOrUserChallengesRequest) : Call<GetOfficialOrUserChallengesResponse>

    @POST("/challenge/getRelatedChallenges")
    fun getChallenge(request: GetRelatedChallengesRequest) : Call<GetRelatedChallengeResponse>

    @POST("/comment/setComment")
    fun setComment(request: SetCommentRequest) : Call<SetCommentResponse>

    @GET("/comment/getAllComments")
    fun getAllComments(proofPostId: Int) : Call<GetAllCommentsResponse>

    @POST("/memberChallenge/joinChallenge")
    fun setChallenge(request: JoinChallengeRequest) : Call<JoinChallengeResponse>

    @POST("/proofPost/setProofPost")
    fun setProofPostRequestResponseEntity(request: SetProofPostRequest) : Call<SetProofPostResponse>

    @GET("/proofPost/getProofPosts")
    fun getProofPost(request: GetProofPostsRequest) : Call<GetProofPostsResponse>
}