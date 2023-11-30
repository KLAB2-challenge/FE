package com.example.klab2challenge

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.klab2challenge.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val challengeViewModel : ChallengeViewModel by viewModel()
    private val rankingViewModel : RankingViewModel by viewModel()
    private val userViewModel : UserViewModel by viewModel()
    private val borderViewModel : BorderViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userName = "user1"
        borderViewModel.requestBorder(userName)
        userViewModel.requestUser(userName)
        rankingViewModel.requestRanking(userName)
        challengeViewModel.requestOfficialChallenges(userName)
        challengeViewModel.requestUserChallenges(userName)
        challengeViewModel.requestPopularChallenges(userName)
        challengeViewModel.requestMyChallenges(userName)



        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController
        val navView: BottomNavigationView = binding.navView
        navView.setupWithNavController(navController)
    }
}