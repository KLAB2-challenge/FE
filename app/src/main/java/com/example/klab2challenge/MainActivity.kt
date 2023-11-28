package com.example.klab2challenge

import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.klab2challenge.databinding.ActivityMainBinding
import com.example.klab2challenge.db.BorderEntity
import com.example.klab2challenge.db.MyDatabase
import com.example.klab2challenge.db.ChallengeEntity
import com.example.klab2challenge.db.RankingEntity
import com.example.klab2challenge.db.UserEntity
import com.example.klab2challenge.retrofit.GetMemberAllBordersRequest
import com.example.klab2challenge.retrofit.GetMemberAllChallengesRequest
import com.example.klab2challenge.retrofit.GetMemberInfosRequest
import com.example.klab2challenge.retrofit.GetOfficialOrUserChallengesRequest
import com.example.klab2challenge.retrofit.GetPopularChallengesRequest
import com.example.klab2challenge.retrofit.RetrofitInterface
import com.example.klab2challenge.retrofit.RetrofitUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val challengeViewModel : ChallengeViewModel by viewModel()
    private val rankingViewModel : RankingViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userName = "user3"
        challengeViewModel.requestOfficialChallenge(userName)
        challengeViewModel.requestUserChallenge(userName)
        challengeViewModel.requestPopularChallenge(userName)
        challengeViewModel.requestMyChallenge(userName)
        rankingViewModel.requestRanking(userName)



//
//        CoroutineScope(Dispatchers.IO).launch {
//            //set User
//            val userEntity = UserEntity(userName, "", 0, "", 0, 0, 0)
//
//            val memberInfoResponse = retrofit.getMemberInfos(GetMemberInfosRequest(userName))
//            if (memberInfoResponse.isSuccessful) {
//                val data = memberInfoResponse.body()!!
//                val infos = data.infos
//                userEntity.image = infos.imageUrl
//                userEntity.currentBorder = infos.currentBorder
//                userEntity.totalCoin = infos.totalCoins
//                userEntity.currentCoin = infos.holdingCoins
//            } else {
//                Log.d("retrofit_main_memberInfo", memberInfoResponse.message().toString())
//            }
//
//            val allBorderResponse =
//                retrofit.getMemberAllBorders(GetMemberAllBordersRequest(userName))
//            if (allBorderResponse.isSuccessful) {
//                val data = allBorderResponse.body()!!
//                userEntity.ownBorders = data.borderIds.toString()
//            } else {
//                Log.d("retrofit_main_allBorders", allBorderResponse.message().toString())
//            }
//
//            //set ranking
//            val rankingResponse = retrofit.getRanking(userName)
//            if (rankingResponse.isSuccessful) {
//                val data = rankingResponse.body()!!
//                userEntity.ranking = data.myRank
//                for (i in 0..<data.ranker.size){
//                    val m = data.ranker[i]
//                    rankingDAO.addRanking(RankingEntity(m.name, m.infos.imageUrl, m.infos.currentBorder, m.infos.totalCoins, i))
//                }
//            } else {
//                Log.d("retrofit_main_ranking", rankingResponse.message().toString())
//            }
//
//            userDao.addUser(userEntity)
//
//            //set Borders
//            borderDAO.addBorder(BorderEntity(0, getColor(R.color.gold), "gold", 20))
//            borderDAO.addBorder(BorderEntity(1, getColor(R.color.green), "green", 40))
//            borderDAO.addBorder(BorderEntity(2, getColor(R.color.cherry), "cherry", 60))
//            borderDAO.addBorder(BorderEntity(3, getColor(R.color.blueberry), "blueberry", 80))
//            borderDAO.addBorder(BorderEntity(4, getColor(R.color.sunny), "sunny", 100))
//            borderDAO.addBorder(BorderEntity(5, getColor(R.color.rainy), "rainy", 120))
//
//            //set HCP
//            val officialHCPResponse = retrofit.getChallenge(GetOfficialOrUserChallengesRequest(userName, 0, 5, true))
//            if (officialHCPResponse.isSuccessful) {
//                val data = officialHCPResponse.body()!!
//                data.challenges.forEach { c ->
//                    hcpDao.addHCP(
//                        ChallengeEntity(
//                            c.challengeId,
//                            c.contents.title,
//                            c.contents.image,
//                            c.memberNum,
//                            c.infos.startDate + " ~ " + c.infos.endDate,
//                            c.infos.frequency,
//                            0.0,
//                            0
//                        )
//                    )
//                }
//            } else {
//                Log.d("retrofit_main_officialHCP", officialHCPResponse.message().toString())
//            }
//
//            val userHCPResponse = retrofit.getChallenge(GetOfficialOrUserChallengesRequest(userName, 0, 5, false))
//            if (userHCPResponse.isSuccessful) {
//                val data = userHCPResponse.body()!!
//                data.challenges.forEach { c ->
//                    hcpDao.addHCP(
//                        ChallengeEntity(
//                            c.challengeId,
//                            c.contents.title,
//                            c.contents.image,
//                            c.memberNum,
//                            c.infos.startDate + " ~ " + c.infos.endDate,
//                            c.infos.frequency,
//                            0.0,
//                            1
//                        )
//                    )
//                }
//            } else {
//                Log.d("retrofit_main_userHCP", userHCPResponse.message().toString())
//            }
//
//            val popularHCPResponse = retrofit.getChallenge(GetPopularChallengesRequest(userName, 0, 5))
//            if (popularHCPResponse.isSuccessful) {
//                val data = popularHCPResponse.body()!!
//                data.challenges.forEach { c ->
//                    hcpDao.addHCP(
//                        ChallengeEntity(
//                            c.challengeId,
//                            c.contents.title,
//                            c.contents.image,
//                            c.memberNum,
//                            c.infos.startDate + " ~ " + c.infos.endDate,
//                            c.infos.frequency,
//                            0.0,
//                            2
//                        )
//                    )
//                }
//            } else {
//                Log.d("retrofit_main_poluarHCP", popularHCPResponse.message().toString())
//            }
//
//            val myChallengeResponse = retrofit.getChallenge(GetMemberAllChallengesRequest(userName, 0, 10))
//            if (myChallengeResponse.isSuccessful) {
//                val data = myChallengeResponse.body()!!
//                data.challenges.forEach { c ->
//                    mcpDao.addMCP(
//                        MCPEntity(
//                            c.challengeId,
//                            c.contents.title,
//                            c.contents.image,
//                            c.memberNum,
//                            c.progressRate
//                        )
//                    )
//                }
//            } else {
//                Log.d("retrofit_main_memberChallengeHCP", myChallengeResponse.message().toString())
//            }
//        }


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController
        val navView: BottomNavigationView = binding.navView
        navView.setupWithNavController(navController)
    }
}