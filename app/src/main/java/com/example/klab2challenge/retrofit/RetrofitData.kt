package com.example.klab2challenge.retrofit

import com.google.gson.annotations.SerializedName


data class Challenge(
    @SerializedName("challenge_id") private val challengeId: Int,
    @SerializedName("contents") private val contents: ChallengeContents,
    @SerializedName("infos") private val infos: ChallengeInfos,
    @SerializedName("user") private val userChallenges: List<UserChallenge>,
    @SerializedName("proofPost") private val proofPosts: List<ProofPost>

)
data class ChallengeContents (
    @SerializedName("title") private val title: String,
    @SerializedName("image") private val image: String,
    @SerializedName("content") private val content: String
)
data class ChallengeInfos (
    @SerializedName("userName") private val userName: String,
    @SerializedName("startDate") private val startDate: String,
    @SerializedName("endDate") private val endDate: String,
    @SerializedName("frequency") private val frequency: String,
    @SerializedName("category") private val category: Int,
    @SerializedName("type") private val type: Boolean
)
data class Comment(
    @SerializedName("commentId") private val commentId: Int,
    @SerializedName("proofPostId") private val proofPostId: Int,
    @SerializedName("userName") private val userName: String
)
data class ProofPost(
    @SerializedName("proofPostId") private val proofPostId: Int,
    @SerializedName("contents") private val contents: ProofPostContents,
    @SerializedName("infos") private val infos: ProofPostInfos,
    @SerializedName("commets") private val comments: List<Comment>
)
data class ProofPostContents (
    @SerializedName("title") private val title: String,
    @SerializedName("content") private val content: String,
    @SerializedName("image") private val image: String
)
data class ProofPostInfos (
    @SerializedName("callengeId") private val challengeId: Int,
    @SerializedName("userName") private val userName: String
)
data class User(
    @SerializedName("userId") private val userId: Int,
    @SerializedName("challenge") private val userChallenges: List<UserChallenge>,
    @SerializedName("comments") private val comments: List<Comment>,
    @SerializedName("proofPost") private val proofPosts: List<ProofPost>
)
data class UserChallenge(
    @SerializedName("id") private val id: Int
)

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
    @SerializedName("commentList") val commentList : List<Comment>
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
    @SerializedName("proofPosts") val proofPosts : List<GetProofPostResponse>
)

data class SetCommentResponse (
    @SerializedName("commentId") val commentId: Int
)

data class SetProofPostResponse (
    @SerializedName("proofPostid") val proofPostId: Int
)

