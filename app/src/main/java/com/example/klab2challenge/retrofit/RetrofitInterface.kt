package com.example.klab2challenge.retrofit

import okhttp3.MultipartBody
import retrofit2.Call
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
    fun getMemberAllBorders(
        @Body request: GetMemberAllBordersRequest
    ): Call<GetMemberAllBordersResponse>

    @Multipart
    @POST("/challenge/setChallenge")
    fun setChallenge(
//        @Part("image") image : MultipartBody.Part,
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

    @POST("/challenge/getMemberAllChallenges")
    fun getChallenge(
        @Body
        request: GetMemberAllChallengesRequest
    ): Call<GetMemberAllChallengesResponse>

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
    fun buyBorder(
        @Body
        request: BuyBorderRequest
    ): Call<BuyBorderResponse>

    @POST("/memberChallenge/joinChallenge")
    fun setChallenge(
        @Body
        request: JoinChallengeRequest
    ): Call<JoinChallengeResponse>

    @POST("/member/setMemberCoin")
    fun setMemberCoins(
        @Body
        request: SetMemberCoinsRequest
    ): Call<SetMemberCoinsResponse>

    @POST("/member/getMemberInfos")
    fun getMemberInfos(
        @Body
        request: GetMemberInfosRequest
    ): Call<GetMemberInfosResponse>

    @GET("/member/changeCurrentBorder")
    fun changeCurrentBorder(
        @Body
        request: ChangeCurrentBorderResponse
    ): Call<ChangeCurrentBorderRequest>

    @GET("/member/getRating")
    fun getRanking(
        @Query("memberName")
        memberName: String
    ): Call<GetRankResponse>

//    @Multipart
    @POST("/challenge/setChallenge")
    fun setProofPost(
//        @Part("image") image : MultipartBody.Part,
//        @Part("proofPost") request: SetChallengeRequest
        @Body request: SetProofPostRequest
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