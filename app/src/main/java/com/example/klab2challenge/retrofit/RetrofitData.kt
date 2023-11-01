package com.example.klab2challenge.retrofit

import com.google.gson.annotations.SerializedName

data class GetAllCommentsRequest (
    @SerializedName("proofPostId") val proofPostId : Int,
    @SerializedName("startPoint") val startPoint : Int
)

data class GetProofPostsRequest (
    @SerializedName("challengeID") val challengeID : Int,
    @SerializedName("count") val count : Int
)

data class SetCommentRequest (
    @SerializedName("content") val content : String,
    @SerializedName("userName") val userName : String,
    @SerializedName("proofPostId") val proofPostId : Int
)

data class SetProofPostRequest (
    @SerializedName("challengeId") val challengeID : Int,
    @SerializedName("userId") val userId : String,
    @SerializedName("title") val title : String,
    @SerializedName("content") val content: String,
    @SerializedName("image") val image : String
)

data class GetAllCommentsResponse (
    @SerializedName("count") val count: Int,
    List<Comment> commentList;
)

data class GetProofPostResponse (
    @SerializedName("proofPostId") val proofPostId: Int,
    @SerializedName("userId") val userId: String,
    @SerializedName("title") val title: String,
    @SerializedName("content") val content: String,
    @SerializedName("image") val image: String,
    @SerializedName("commentsNum") val commentsNum: Int
)

data class GetProofPostsResponse (
    List<GetProofPostResponse> proofPosts;
)

data class SetCommentResponse (
    @SerializedName("commentId") val commentId: Int
)

data class SetProofPostResponse (
    @SerializedName("proofPostid") val proofPostId: Int
)