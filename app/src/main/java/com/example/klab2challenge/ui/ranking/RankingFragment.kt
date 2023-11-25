package com.example.klab2challenge.ui.ranking

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.klab2challenge.databinding.FragmentRankingBinding
import com.example.klab2challenge.retrofit.GetRankResponse
import com.example.klab2challenge.retrofit.RetrofitUtil
import com.example.klab2challenge.retrofit.getUserName
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RankingFragment : Fragment() {

    private var _binding: FragmentRankingBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    var rankingViewModel = RankingViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(RankingViewModel::class.java)

        _binding = FragmentRankingBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.rvRankingAllRankings.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvRankingAllRankings.adapter = RankingAdapter(rankingViewModel.itemList.value!!)
        rankingViewModel.itemList.observe(viewLifecycleOwner, Observer {
            (binding.rvRankingAllRankings.adapter as RankingAdapter).setData(it)
        })

        RetrofitUtil.getRetrofitUtil().getRanking(getUserName(requireContext())).enqueue(object:
            Callback<GetRankResponse> {
            override fun onResponse(
                call: Call<GetRankResponse>,
                response: Response<GetRankResponse>
            ) {
                if(response.isSuccessful) {
                    val rankingList = response.body()!!.ranker
                    binding.tvRankingTop1Profile.text = rankingList.get(0).name
                    binding.tvRankingTop2Profile.text = rankingList.get(1).name
                    binding.tvRankingTop3Profile.text = rankingList.get(2).name
                    binding.tvRankingTop1Coin.text = rankingList.get(0).infos.holdingCoins.toString()
                    binding.tvRankingTop2Coin.text = rankingList.get(1).infos.holdingCoins.toString()
                    binding.tvRankingTop3Coin.text = rankingList.get(2).infos.holdingCoins.toString()

                    rankingViewModel.setItem(rankingList.subList(3, rankingList.size-1))
                } else {
                    Log.d("hyunhee", response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<GetRankResponse>, t: Throwable) {
                Log.d("hyunhee", t.message!!)
            }

        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}