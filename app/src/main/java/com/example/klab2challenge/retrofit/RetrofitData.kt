package com.example.klab2challenge.retrofit

import com.google.gson.annotations.SerializedName


data class Challenge(
    @SerializedName("challenge_id") private val challengeId: Int? = null,
    @SerializedName("contents") private val contents: ChallengeContents,
    @SerializedName("infos") private val infos: ChallengeInfos,
    @SerializedName("user") private val userChallenges: List<UserChallenge> = ArrayList<UserChallenge>(),
    @SerializedName("proofPost") private val proofPosts: List<ProofPost> = ArrayList<ProofPost>()

)
data class ChallengeContents (
    @SerializedName("title") private val title: String? = null,
    @SerializedName("image") private val image: String? = null,
    @SerializedName("content") private val content: String? = null
)
data class ChallengeInfos (
    @SerializedName("userName") private val userName: String? = null,
    @SerializedName("startDate") private val startDate: String? = null,
    @SerializedName("endDate") private val endDate: String? = null,
    @SerializedName("frequency") private val frequency: String? = null,
    @SerializedName("category") private val category: Int? = null,
    @SerializedName("type") private val type: Boolean? = null
)
data class Comment(
    @SerializedName("commentId") private val commentId: Int? = null,
    @SerializedName("proofPostId") private val proofPostId: Int,
    @SerializedName("userName") private val userName: String
)
data class ProofPost(
    @SerializedName("proofPostId") private val proofPostId: Long? = null,
    @SerializedName("contents") private val contents: ProofPostContents,
    @SerializedName("infos") private val infos: ProofPostInfos,
    @SerializedName("commets") private val comments: List<Comment> = ArrayList()
)
data class ProofPostContents (
    @SerializedName("title") private val title: String? = null,
    @SerializedName("content") private val content: String? = null,
    @SerializedName("image") private val image: String? = null
)
data class ProofPostInfos (
    @SerializedName("callengeId") private val challengeId: Long? = null,
    @SerializedName("userName") private val userName: String? = null
)
data class User(
    @SerializedName("userId") private val userId: Long? = null,
    @SerializedName("challenge") private val userChallenges: List<UserChallenge> = ArrayList<UserChallenge>(),
    @SerializedName("comments") private val comments: List<Comment> = ArrayList(),
    @SerializedName("proofPost") private val proofPosts: List<ProofPost> = ArrayList()
)
data class UserChallenge(
    @SerializedName("id") private val id: Int? = null
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

