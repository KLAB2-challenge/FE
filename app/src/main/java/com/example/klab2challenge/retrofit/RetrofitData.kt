package com.example.klab2challenge.retrofit

import com.google.gson.annotations.SerializedName
import java.lang.reflect.Member


data class Challenge(
    @SerializedName("challengeId") private val challengeId: Int,
    @SerializedName("member") val member: Member,
    @SerializedName("contents") private val contents: ChallengeContents,
    @SerializedName("infos") private val infos: ChallengeInfos,
    @SerializedName("memberChallenges") private val memberChallenges: List<MemberChallenge>,
    @SerializedName("proofPost") private val proofPosts: List<ProofPost>

)
data class ChallengeContents (
    @SerializedName("title") private val title: String,
    @SerializedName("content") private val content: String,
    @SerializedName("image") private val image: String
)
data class ChallengeInfos (
    @SerializedName("startDate") private val startDate: String,
    @SerializedName("endDate") private val endDate: String,
    @SerializedName("frequency") private val frequency: String,
    @SerializedName("category") private val category: Int,
    @SerializedName("type") private val type: Boolean
)
data class Comment(
    @SerializedName("commentId") private val commentId: Int,
    @SerializedName("proofPost") private val proofPost: ProofPost,
    @SerializedName("member") private val member: Member,
    @SerializedName("content") private val content: String
)

data class Member (
    @SerializedName("memberId") val memberId: Int,
    @SerializedName("name") val name: String,
    @SerializedName("challenges") val challenges: List<Challenge>,
    @SerializedName("memberChallenges") val memberChallenges: List<MemberChallenge>,
    @SerializedName("comments") val comments: List<Comment>,
    @SerializedName("proofPosts") val proofPosts: List<ProofPost>
)

data class MemberChallenge (
    @SerializedName("id") val id: Int,
    @SerializedName("member") val member: Member,
    @SerializedName("challenge") val challenge: Challenge
)

data class ProofPost(
    @SerializedName("proofPostId") private val proofPostId: Int,
    @SerializedName("contents") private val contents: ProofPostContents,
    @SerializedName("challenge") val challenge: Challenge,
    @SerializedName("member") val member: Member,
    @SerializedName("comments") private val comments: List<Comment>
)
data class ProofPostContents (
    @SerializedName("title") private val title: String,
    @SerializedName("content") private val content: String,
    @SerializedName("image") private val image: String
)

data class GetChallengeRequest (
    @SerializedName("memberName") val memberName : String,
    @SerializedName("challengeId") val challengeId : Int
)

data class GetOfficialOrUserChallengesRequest (
    @SerializedName("memberName") val memberName: String,
    @SerializedName("page") val page: Int,
    @SerializedName("size") val size: Int,
    @SerializedName("type") val type: Boolean,
)

data class GetPopularChallengesRequest (
    @SerializedName("memberName") val memberName: String,
    @SerializedName("page") val page: Int,
    @SerializedName("size") val size: Int
)


data class GetProofPostsRequest (
    @SerializedName("challengeID") val challengeID : Int,
    @SerializedName("num") val num : Int
)

data class GetRelatedChallengesRequest (
    @SerializedName("memberName") val memberName: String,
    @SerializedName("page") val page: Int,
    @SerializedName("size") val size: Int,
    @SerializedName("category") val category: Int
)

data class JoinChallengeRequest (
    @SerializedName("memberName") val memberName: String,
    @SerializedName("challengeID") val challengeID : Int
)

data class SetChallengeRequest (
    @SerializedName("memberName") val memberName: String,
    @SerializedName("contents") val contents: ChallengeContents,
    @SerializedName("infos") val infos: ChallengeInfos
)

data class SetCommentRequest (
    @SerializedName("memberName") val memberName: String,
    @SerializedName("contents") val contents: String,
    @SerializedName("proofPostId") val proofPostId : Int
)

data class SetProofPostRequest (
    @SerializedName("challengeId") val challengeID : Int,
    @SerializedName("memberName") val memberName: String,
    @SerializedName("title") val title : String,
    @SerializedName("content") val content: String,
    @SerializedName("image") val image : String
)

data class GetAllCommentsResponse (
    @SerializedName("getCommentResponses") val getCommentResponses : List<GetCommentResponse>
)

data class GetChallengeResponse (
    @SerializedName("contents") val contents: ChallengeContents,
    @SerializedName("infos") val infos: ChallengeInfos,
    @SerializedName("memberNum") val memberNum: Int,
    @SerializedName("isJoin") val isJoin: Boolean
)

data class GetCommentResponse (
    @SerializedName("memberName") val memberName: String,
    @SerializedName("content") val content: String
)

data class GetOfficialOrUserChallengesResponse (
    @SerializedName("challenges") val challenges: List<GetChallengeResponse>
)

data class GetPopularChallengesResponse (
    @SerializedName("challenges") val challenges: List<GetChallengeResponse>
)

data class GetProofPostResponse (
    @SerializedName("proofPostId") val proofPostId: Int,
    @SerializedName("memberName") val memberName: String,
    @SerializedName("title") val title: String,
    @SerializedName("content") val content: String,
    @SerializedName("image") val image: String
)

data class GetProofPostsResponse (
    @SerializedName("proofPosts") val proofPosts : List<GetProofPostResponse>
)

data class GetRelatedChallengesResponse (
    @SerializedName("challenges") val challenges : List<GetChallengeResponse>
)

data class JoinChallengeResponse (
    @SerializedName("challengeId") val challengeID: Int
)

data class SetChallengeResponse (
    @SerializedName("challengeId") val challengeID: Int
)

data class SetCommentResponse (
    @SerializedName("commentId") val commentId: Int
)

data class SetProofPostResponse (
    @SerializedName("proofPostId") val proofPostId: Int
)

