package com.example.klab2challenge.retrofit

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitInterface {

//    @GET("/")
//    fun SampleAPI(sampleRequest: SampleRequest) : Call<SampleResponse>

    //안해도 됨 demo는

    @POST("/border/getMemberAllBorders")
    suspend fun getMemberAllBorders(
        @Body request: GetMemberAllBordersRequest
    ): Response<GetMemberAllBordersResponse>

    @Multipart
    @POST("/challenge/setChallenge")
    fun setChallenge(
        @Part image : MultipartBody.Part,
        @Part("challenge") request: SetChallengeRequest
//        @Body request: SetChallengeRequest
    ): Call<SetChallengeResponse>

    @POST("/challenge/getChallenge")
    fun getChallenge(
        @Body
        request: GetChallengeRequest
    ): Call<GetChallengeResponse>

    //완료
    @POST("/challenge/getPopularChallenges")
    suspend fun getChallenge(
        @Body
        request: GetPopularChallengesRequest
    ): Response<GetPopularChallengesResponse>

    //완료
    @POST("/challenge/getOfficialOrUserChallenges")
    suspend fun getChallenge(
        @Body
        request: GetOfficialOrUserChallengesRequest
    ): Response<GetOfficialOrUserChallengesResponse>

    //완료
    @POST("/challenge/getRelatedChallenges")
    fun getChallenge(
        @Body
        request: GetRelatedChallengesRequest
    ): Call<GetRelatedChallengesResponse>

    @POST("/challenge/getMemberAllChallenges")
    suspend fun getChallenge(
        @Body
        request: GetMemberAllChallengesRequest
    ): Response<GetMemberAllChallengesResponse>

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

    @POST ("/memberBorder/buyBorder")
    suspend fun buyBorder(
        @Body
        request: BuyBorderRequest
    ): Response<BuyBorderResponse>

    @POST("/memberChallenge/joinChallenge")
    fun setChallenge(
        @Body
        request: JoinChallengeRequest
    ): Call<JoinChallengeResponse>

    @POST("/member/setMemberCoin")
    suspend fun setMemberCoins(
        @Body
        request: SetMemberCoinsRequest
    ): Response<SetMemberCoinsResponse>

    @POST("/member/getMemberInfos")
    suspend fun getMemberInfos(
        @Body
        request: GetMemberInfosRequest
    ): Response<GetMemberInfosResponse>

    @GET("/member/changeCurrentBorder")
    suspend fun changeCurrentBorder(
        @Body
        request: ChangeCurrentBorderRequest
    ): Response<ChangeCurrentBorderResponse>

    @GET("/member/getRating")
    suspend fun getRanking(
        @Query("memberName")
        memberName: String
    ): Response<GetRankResponse>

    @Multipart
    @POST("/proofPost/setProofPost")
    fun setProofPost(
        @Part image : MultipartBody.Part,
        @Part("proofPost") request: SetProofPostRequest
//        @Body request: SetProofPostRequest
    ): Call<SetProofPostResponse>

    @GET("/proofPost/getProofPost")
    fun getProofPost(
        @Query("proofPostId")
        proofPostId: Int
    ): Call<GetProofPostResponse>

    @GET("/proofPost/getAllProofPosts")
    fun getProofPosts(
        @Query("challengeId") challengeId : Int
    ): Call<GetProofPostsResponse>

    @GET("/proofPost/getProofPosts")
    fun getProofPost(
        @Body
        request: GetProofPostsRequest
    ): Call<GetProofPostsResponse>
}