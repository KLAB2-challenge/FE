package com.example.klab2challenge.ui.home

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.klab2challenge.R
import com.example.klab2challenge.databinding.FragmentHomeBinding
import com.example.klab2challenge.retrofit.GetChallengeResponse
import com.example.klab2challenge.retrofit.GetOfficialOrUserChallengesRequest
import com.example.klab2challenge.retrofit.GetOfficialOrUserChallengesResponse
import com.example.klab2challenge.retrofit.GetPopularChallengesRequest
import com.example.klab2challenge.retrofit.GetPopularChallengesResponse
import com.example.klab2challenge.retrofit.RetrofitUtil
import com.example.klab2challenge.ui.challenge.ChallengeDetailActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!
    private val popularViewModel = ChallengeViewModel()
    private val officialViewModel = ChallengeViewModel()
    private val userViewModel = ChallengeViewModel()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.rvHomePopular.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val popularAdapter = ChallengeAdapter(popularViewModel.itemList.value!!)
        popularAdapter.itemClickListener = object : ChallengeAdapter.OnItemClickListener {
            override fun onItemClicked() {
                val i = Intent(requireContext(), ChallengeDetailActivity::class.java)
                startActivity(i)
            }
        }
        binding.rvHomePopular.adapter = popularAdapter
        popularViewModel.itemList.observe(viewLifecycleOwner, Observer {
            (binding.rvHomePopular.adapter as ChallengeAdapter).setData(it)
        })

        binding.rvHomeOfficial.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val officialAdapter = ChallengeAdapter(officialViewModel.itemList.value!!)
        officialAdapter.itemClickListener = object : ChallengeAdapter.OnItemClickListener {
            override fun onItemClicked() {
                val i = Intent(requireContext(), ChallengeDetailActivity::class.java)
                startActivity(i)
            }
        }
        binding.rvHomeOfficial.adapter = officialAdapter
        officialViewModel.itemList.observe(viewLifecycleOwner, Observer {
            (binding.rvHomeOfficial.adapter as ChallengeAdapter).setData(it)
        })

        binding.rvHomeUser.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val userAdapter = ChallengeAdapter(userViewModel.itemList.value!!)
        userAdapter.itemClickListener = object : ChallengeAdapter.OnItemClickListener {
            override fun onItemClicked() {
                val i = Intent(requireContext(), ChallengeDetailActivity::class.java)
                startActivity(i)
            }
        }
        binding.rvHomeUser.adapter = userAdapter
        userViewModel.itemList.observe(viewLifecycleOwner, Observer {
            (binding.rvHomeUser.adapter as ChallengeAdapter).setData(it)
        })

        val popularRequest = GetPopularChallengesRequest("user", 0, 5)
        RetrofitUtil.getRetrofitUtil().getChallenge(popularRequest).enqueue(object : Callback<GetPopularChallengesResponse> {
            override fun onResponse(
                call: Call<GetPopularChallengesResponse>,
                response: Response<GetPopularChallengesResponse>
            ) {
                if(response.isSuccessful) {
                    popularViewModel.addChallenges(response.body()!!.challenges)
                } else {
                    Log.d("seohyun", response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<GetPopularChallengesResponse>, t: Throwable) {
                Log.d("seohyun", t.message.toString())
            }
        })

        val officialRequest = GetOfficialOrUserChallengesRequest("user",0,5,true)
        RetrofitUtil.getRetrofitUtil().getChallenge(officialRequest).enqueue(object :Callback<GetOfficialOrUserChallengesResponse> {
            override fun onResponse(
                call: Call<GetOfficialOrUserChallengesResponse>,
                response: Response<GetOfficialOrUserChallengesResponse>
            ) {
                if(response.isSuccessful) {
                    officialViewModel.addChallenges(response.body()!!.challenges)
                } else {
                    Log.d("seohyun", response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<GetOfficialOrUserChallengesResponse>, t: Throwable) {
                Log.d("seohyun", t.message.toString())
            }

        })

        val userRequest = GetOfficialOrUserChallengesRequest("user",1,5,false)
        RetrofitUtil.getRetrofitUtil().getChallenge(userRequest).enqueue(object : Callback<GetOfficialOrUserChallengesResponse> {
            override fun onResponse(
                call: Call<GetOfficialOrUserChallengesResponse>,
                response: Response<GetOfficialOrUserChallengesResponse>
            ) {
                if(response.isSuccessful) {
                    userViewModel.addChallenges(response.body()!!.challenges)
                } else {
                    Log.d("seohyun", response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<GetOfficialOrUserChallengesResponse>, t: Throwable) {
                Log.d("seohyun", t.message.toString())
            }

        })


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}