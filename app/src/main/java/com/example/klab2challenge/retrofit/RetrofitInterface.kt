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
    suspend fun setChallenge(
        @Part image : MultipartBody.Part? = null,
        @Part("challenge") request: SetChallengeRequest
    ): Response<SetChallengeResponse>

    @POST("/challenge/getChallenge")
    suspend fun getChallenge(
        @Body
        request: GetChallengeRequest
    ): Response<GetChallengeResponse>

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
    suspend fun getChallenge(
        @Body
        request: GetRelatedChallengesRequest
    ): Response<GetRelatedChallengesResponse>

    @POST("/challenge/getMemberAllChallenges")
    suspend fun getChallenge(
        @Body
        request: GetMemberAllChallengesRequest
    ): Response<GetMemberAllChallengesResponse>

    @POST("/comment/setComment")
    suspend fun setComment(
        @Body
        request: SetCommentRequest
    ): Response<SetCommentResponse>

    @GET("/comment/getAllComments")
    suspend fun getAllComments(
        @Query("proofPostId")
        proofPostId: Int
    ): Response<GetAllCommentsResponse>

    @POST ("/memberBorder/buyBorder")
    suspend fun buyBorder(
        @Body
        request: BuyBorderRequest
    ): Response<BuyBorderResponse>

    @POST("/memberChallenge/joinChallenge")
    suspend fun setChallenge(
        @Body
        request: JoinChallengeRequest
    ): Response<JoinChallengeResponse>

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

    @POST("/member/changeCurrentBorder")
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
    suspend fun setProofPost(
        @Part image : MultipartBody.Part? = null,
        @Part("proofPost") request: SetProofPostRequest
//        @Body request: SetProofPostRequest
    ): Response<SetProofPostResponse>

    @GET("/proofPost/getProofPost")
    suspend fun getProofPost(
        @Query("proofPostId")
        proofPostId: Int
    ): Response<GetProofPostResponse>

    @GET("/proofPost/getAllProofPosts")
    suspend fun getProofPosts(
        @Query("challengeId") challengeId : Int
    ): Response<GetProofPostsResponse>
}