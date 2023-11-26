package com.example.klab2challenge.ui.home

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.klab2challenge.databinding.FragmentHomeBinding
import com.example.klab2challenge.retrofit.GetOfficialOrUserChallengesRequest
import com.example.klab2challenge.retrofit.GetOfficialOrUserChallengesResponse
import com.example.klab2challenge.retrofit.GetPopularChallengesRequest
import com.example.klab2challenge.retrofit.GetPopularChallengesResponse
import com.example.klab2challenge.retrofit.RetrofitUtil
import com.example.klab2challenge.retrofit.getUserName
import com.example.klab2challenge.ui.challenge.ChallengeDetailActivity
import com.example.klab2challenge.ui.challenge.AddChallengeActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!
    private val popularViewModel = ChallengeViewModel()
    private val officialViewModel = ChallengeViewModel()
    private val userViewModel = ChallengeViewModel()

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            Log.d("hyunheeResult", it.toString())
//            if(it.resultCode == Activity.RESULT_OK) {
//                getChallenges()
//            }
        }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.rvHomePopular.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val popularAdapter = ChallengeAdapter(requireContext(), popularViewModel.itemList.value!!)
        popularAdapter.itemClickListener = object : ChallengeAdapter.OnItemClickListener {
            override fun onItemClicked(challengeId : Int) {
                val i = Intent(requireContext(), ChallengeDetailActivity::class.java)
                i.putExtra("challengeId", challengeId)
                launcher.launch(i)
            }
        }
        binding.rvHomePopular.adapter = popularAdapter
        popularViewModel.itemList.observe(viewLifecycleOwner, Observer {
            (binding.rvHomePopular.adapter as ChallengeAdapter).setData(it)
        })

        binding.rvHomeOfficial.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val officialAdapter = ChallengeAdapter(requireContext(), officialViewModel.itemList.value!!)
        officialAdapter.itemClickListener = object : ChallengeAdapter.OnItemClickListener {
            override fun onItemClicked(challengeId : Int) {
                val i = Intent(requireContext(), ChallengeDetailActivity::class.java)
                i.putExtra("challengeId", challengeId)
                startActivity(i)
            }
        }
        binding.rvHomeOfficial.adapter = officialAdapter
        officialViewModel.itemList.observe(viewLifecycleOwner, Observer {
            (binding.rvHomeOfficial.adapter as ChallengeAdapter).setData(it)
        })

        binding.rvHomeUser.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val userAdapter = ChallengeAdapter(requireContext(), userViewModel.itemList.value!!)
        userAdapter.itemClickListener = object : ChallengeAdapter.OnItemClickListener {
            override fun onItemClicked(challengeId : Int) {
                val i = Intent(requireContext(), ChallengeDetailActivity::class.java)
                i.putExtra("challengeId", challengeId)
                startActivity(i)
            }
        }
        binding.rvHomeUser.adapter = userAdapter
        userViewModel.itemList.observe(viewLifecycleOwner, Observer {
            (binding.rvHomeUser.adapter as ChallengeAdapter).setData(it)
        })

        binding.fabHomeAdd.setOnClickListener {
            val i = Intent(requireContext(), AddChallengeActivity::class.java)
            startActivity(i)
        }

        getChallenges()


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun getChallenges() {
        val popularRequest = GetPopularChallengesRequest(getUserName(requireContext()), 0, 5)
        RetrofitUtil.getRetrofitUtil().getChallenge(popularRequest).enqueue(object : Callback<GetPopularChallengesResponse> {
            override fun onResponse(
                call: Call<GetPopularChallengesResponse>,
                response: Response<GetPopularChallengesResponse>
            ) {
                if(response.isSuccessful) {
                    popularViewModel.clearChallenge()
                    popularViewModel.addChallenges(response.body()!!.challenges)
                } else {
                    Log.d("seohyun", response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<GetPopularChallengesResponse>, t: Throwable) {
                Log.d("seohyun", t.message.toString())
            }
        })

        val officialRequest = GetOfficialOrUserChallengesRequest(getUserName(requireContext()),0,5,true)
        RetrofitUtil.getRetrofitUtil().getChallenge(officialRequest).enqueue(object :Callback<GetOfficialOrUserChallengesResponse> {
            override fun onResponse(
                call: Call<GetOfficialOrUserChallengesResponse>,
                response: Response<GetOfficialOrUserChallengesResponse>
            ) {
                if(response.isSuccessful) {
                    officialViewModel.clearChallenge()
                    officialViewModel.addChallenges(response.body()!!.challenges)
                } else {
                    Log.d("seohyun", response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<GetOfficialOrUserChallengesResponse>, t: Throwable) {
                Log.d("seohyun", t.message.toString())
            }

        })

        val userRequest = GetOfficialOrUserChallengesRequest(getUserName(requireContext()),0,5,false)
        RetrofitUtil.getRetrofitUtil().getChallenge(userRequest).enqueue(object : Callback<GetOfficialOrUserChallengesResponse> {
            override fun onResponse(
                call: Call<GetOfficialOrUserChallengesResponse>,
                response: Response<GetOfficialOrUserChallengesResponse>
            ) {
                if(response.isSuccessful) {
                    userViewModel.clearChallenge()
                    userViewModel.addChallenges(response.body()!!.challenges)
                } else {
                    Log.d("seohyun", response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<GetOfficialOrUserChallengesResponse>, t: Throwable) {
                Log.d("seohyun", t.message.toString())
            }

        })
    }
}