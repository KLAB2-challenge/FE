package com.example.klab2challenge.ui.ranking

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.klab2challenge.R
import com.example.klab2challenge.databinding.FragmentRankingBinding
import com.example.klab2challenge.retrofit.GetRankResponse
import com.example.klab2challenge.retrofit.RetrofitUtil
import com.example.klab2challenge.retrofit.getUserBorder
import com.example.klab2challenge.retrofit.getUserName
import com.example.klab2challenge.retrofit.getUserProfileUrl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RankingFragment : Fragment() {

    private var _binding: FragmentRankingBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    var rankingViewModel = RankingViewModel()
    lateinit var color: ArrayList<Int>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(RankingViewModel::class.java)

        _binding = FragmentRankingBinding.inflate(inflater, container, false)
        val root: View = binding.root

        color = arrayListOf(
            ContextCompat.getColor(requireContext(), R.color.gold),
            ContextCompat.getColor(requireContext(), R.color.green),
            ContextCompat.getColor(requireContext(), R.color.cherry),
            ContextCompat.getColor(requireContext(), R.color.blueberry),
            ContextCompat.getColor(requireContext(), R.color.sunny),
            ContextCompat.getColor(requireContext(), R.color.rainy)
        )

        Glide.with(this).load(getUserProfileUrl(requireContext())).into(binding.ivRankingProfileImg)


        binding.rvRankingAllRankings.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvRankingAllRankings.adapter =
            RankingAdapter(requireContext(), rankingViewModel.itemList.value!!)
        rankingViewModel.itemList.observe(viewLifecycleOwner, Observer {
            (binding.rvRankingAllRankings.adapter as RankingAdapter).setData(it)
        })


        binding.tvRankingProfileName.text = getUserName(requireContext())
        Log.d("hyunheerankborder", getUserBorder(requireContext()).toString())
        binding.cvRankingProfileImgBorder.backgroundTintList = ColorStateList.valueOf(getUserBorder(requireContext()))

        RetrofitUtil.getRetrofitUtil().getRanking(getUserName(requireContext())).enqueue(object :
            Callback<GetRankResponse> {
            override fun onResponse(
                call: Call<GetRankResponse>,
                response: Response<GetRankResponse>
            ) {
                Log.d("hyunherankqq", response.body().toString())
                if (response.isSuccessful) {
                    val rankingList = response.body()!!.ranker
                    val myRanking = response.body()!!.myRank
                    binding.tvRankingMyRank.text = "# " + myRanking
                    binding.tvRankingCoin.text =
                        rankingList.get(myRanking - 1).infos.holdingCoins.toString()

                    binding.cvRankingTop1Border.backgroundTintList =
                        ColorStateList.valueOf(color.get(rankingList.get(0).infos.currentBorder))
                    binding.tvRankingTop1Profile.text = rankingList.get(0).name
                    binding.tvRankingTop1Coin.text = rankingList.get(0).infos.holdingCoins.toString()
                    binding.cvRankingTop1Border.backgroundTintList = ColorStateList.valueOf(color.get(rankingList.get(0).infos.currentBorder))
                    Glide.with(this@RankingFragment).load(rankingList.get(0).infos.imageUrl).into(binding.ivRankingTop1)
                    if (rankingList.size >= 2) {
                        binding.tvRankingTop2Profile.text = rankingList.get(1).name
                        binding.tvRankingTop2Coin.text = rankingList.get(1).infos.holdingCoins.toString()
                        binding.cvRankingTop2Border.backgroundTintList = ColorStateList.valueOf(color.get(rankingList.get(1).infos.currentBorder))
                        Glide.with(this@RankingFragment).load(rankingList.get(1).infos.imageUrl).into(binding.ivRankingTop2)
                    }
                    if (rankingList.size >= 3) {
                        binding.tvRankingTop3Profile.text = rankingList.get(2).name
                        binding.tvRankingTop3Coin.text = rankingList.get(2).infos.holdingCoins.toString()
                        binding.cvRankingTop3Border.backgroundTintList = ColorStateList.valueOf(color.get(rankingList.get(2).infos.currentBorder))
                        Glide.with(this@RankingFragment).load(rankingList.get(2).infos.imageUrl).into(binding.ivRankingTop3)
                    }
                    if (rankingList.size >= 4) {
                        rankingViewModel.setItem(rankingList.subList(4, rankingList.size - 1))
                    }
                } else {
                    Log.d("hyunherankww", response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<GetRankResponse>, t: Throwable) {
                t.printStackTrace()
            }

        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}