package com.example.klab2challenge.retrofit

import com.google.gson.annotations.SerializedName

data class Border(
    @SerializedName("id") val id: Int,
    @SerializedName("memberBorders") val memberBorders: List<MemberBorder>
)
data class Challenge(
    @SerializedName("challengeId")  val challengeId: Int,
    @SerializedName("member") val member: Member,
    @SerializedName("contents")  val contents: ChallengeContents,
    @SerializedName("infos")  val infos: ChallengeInfos,
    @SerializedName("memberChallenges")  val memberChallenges: List<MemberChallenge>,
    @SerializedName("proofPosts")  val proofPosts: List<ProofPost>
)
data class ChallengeContents (
    @SerializedName("title") val title: String,
    @SerializedName("content") val content: String,
    @SerializedName("image") val image: String
)
data class ChallengeInfos (
    @SerializedName("startDate")  val startDate: String,
    @SerializedName("endDate")  val endDate: String,
    @SerializedName("frequency")  val frequency: String,
    @SerializedName("category")  val category: Int,
    @SerializedName("type")  val type: Boolean
)
data class Comment(
    @SerializedName("commentId")  val commentId: Int,
    @SerializedName("commentInfos")  val commentInfos: CommentInfos,
    @SerializedName("proofPost")  val proofPost: ProofPost,
    @SerializedName("member")  val member: Member,
    @SerializedName("content")  val content: String
)

data class CommentInfos(
    @SerializedName("date") val date: String
)

data class Member (
    @SerializedName("memberId") val memberId: Int,
    @SerializedName("name") val name: String,
    @SerializedName("infos") val infos: MemberInfos,
    @SerializedName("challenges") val challenges: List<Challenge>,
    @SerializedName("memberChallenges") val memberChallenges: List<MemberChallenge>,
    @SerializedName("comments") val comments: List<Comment>,
    @SerializedName("proofPosts") val proofPosts: List<ProofPost>,
    @SerializedName("memberBorders") val memberBorders: List<MemberBorder>
)

data class MemberBorder (
    @SerializedName("id") val id: Int,
    @SerializedName("member") val member: Member,
    @SerializedName("border") val border: Border,
)

data class MemberChallenge (
    @SerializedName("id") val id: Int,
    @SerializedName("member") val member: Member,
    @SerializedName("challenge") val challenge: Challenge
)

data class MemberInfos (
    @SerializedName("totalCoins") val totalCoins: Int,
    @SerializedName("holdingCoins") val holdingCoins: Int,
    @SerializedName("currentBorder") val currentBorder: Int,
    @SerializedName("imageUrl") val imageUrl: String,
)

data class ProofPost(
    @SerializedName("proofPostId")  val proofPostId: Int,
    @SerializedName("contents")  val contents: ProofPostContents,
    @SerializedName("infos")  val infos: ProofPostInfos,
    @SerializedName("challenge") val challenge: Challenge,
    @SerializedName("member") val member: Member,
    @SerializedName("comments")  val comments: List<Comment>
)
data class ProofPostContents (
    @SerializedName("title")  val title: String,
    @SerializedName("content")  val content: String,
    @SerializedName("image")  val image: String
)

data class ProofPostInfos (
    @SerializedName("date") val date: String
)

data class BuyBorderRequest (
    @SerializedName("memberName") val memberName: String,
    @SerializedName("borderId") val borderId: Int
)

data class ChangeCurrentBorderRequest (
    @SerializedName("memberName") val memberName: String,
    @SerializedName("borderId") val borderId: Int
)

data class GetChallengeRequest (
    @SerializedName("memberName") val memberName : String,
    @SerializedName("challengeId") val challengeId : Int
)

data class GetMemberAllBordersRequest (
    @SerializedName("memberName") val memberName: String
)

data class GetMemberAllChallengesRequest (
    @SerializedName("memberName") val memberName: String,
    @SerializedName("page") val page: Int,
    @SerializedName("size") val size: Int
)

data class GetMemberInfosRequest (
    @SerializedName("memberName") val memberName: String
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
    @SerializedName("page") val page : Int,
    @SerializedName("size") val size: Int
)

data class GetRelatedChallengesRequest (
    @SerializedName("memberName") val memberName: String,
    @SerializedName("page") val page: Int,
    @SerializedName("size") val size: Int,
    @SerializedName("category") val category: Int
)

data class JoinChallengeRequest (
    @SerializedName("memberName") val memberName: String,
    @SerializedName("challengeId") val challengeID : Int
)

data class SetChallengeRequest (
    @SerializedName("memberName") val memberName: String,
    @SerializedName("contents") val contents: ChallengeContents,
    @SerializedName("infos") val infos: ChallengeInfos
)

data class SetCommentRequest (
    @SerializedName("memberName") val memberName: String,
    @SerializedName("content") val contents: String,
    @SerializedName("proofPostId") val proofPostId : Int
)

data class SetMemberCoinsRequest (
    @SerializedName("memberName") val memberName: String,
    @SerializedName("coins") val coins: Int
)

data class SetProofPostRequest (
    @SerializedName("challengeId") val challengeID : Int,
    @SerializedName("memberName") val memberName: String,
    @SerializedName("contents") val contents: ProofPostContents,
)

data class BuyBorderResponse (
    @SerializedName("success") val success: Boolean
)

data class ChangeCurrentBorderResponse (
    @SerializedName("success") val success: Boolean
)

data class GetAllCommentsResponse (
    @SerializedName("getCommentResponses") val getCommentResponses : List<GetCommentResponse>
)

data class GetChallengeResponse (
    @SerializedName("challengeId") val challengeId: Int,
    @SerializedName("contents") val contents: ChallengeContents,
    @SerializedName("infos") val infos: ChallengeInfos,
    @SerializedName("memberNum") val memberNum: Int,
    @SerializedName("join") var join: Boolean,
    @SerializedName("progressRate")  val progressRate: Double
)

data class GetCommentResponse (
    @SerializedName("memberName") val memberName: String,
    @SerializedName("content") val content: String,
    @SerializedName("infos") val infos: CommentInfos,
    @SerializedName("memberCurrentBorder") val memberCurrentBorder: Int,
    @SerializedName("memberImageUrl") val memberImageUrl: String
)

data class GetMemberAllBordersResponse (
    @SerializedName("borders") val borders: List<Border>
)

data class GetMemberAllChallengesResponse (
    @SerializedName("challenges") val challenges: List<GetChallengeResponse>
)

data class GetMemberInfosResponse (
    @SerializedName("memberName") val memberName: String,
    @SerializedName("infos") val infos: MemberInfos,
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
    @SerializedName("contents") val contents: ProofPostContents,
    @SerializedName("infos") val infos: ProofPostInfos,
    @SerializedName("commentNum") val commentNum: Int,
    @SerializedName("memberCurrentBorder") val memberCurrentBorder: Int,
    @SerializedName("memberImageUrl") val memberImageUrl: String
)

data class GetProofPostsResponse (
    @SerializedName("proofPosts") val proofPosts : List<GetProofPostResponse>
)

data class GetRankResponse (
    @SerializedName("my_rank") val myRank: Int,
    @SerializedName("ranker") val ranker: List<Member>,
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

data class SetMemberCoinsResponse (
    @SerializedName("success") val success: Boolean
)

data class SetProofPostResponse (
    @SerializedName("proofPostId") val proofPostId: Int
)