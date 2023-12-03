package com.example.klab2challenge.ui.ranking

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.klab2challenge.databinding.FragmentRankingBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RankingFragment : Fragment() {

    private var _binding: FragmentRankingBinding? = null
    private val binding get() = _binding!!

    private val rankingFragmentViewModel : RankingFragmentViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRankingBinding.inflate(inflater, container, false)
        val root: View = binding.root


        binding.rvRankingAllRankings.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvRankingAllRankings.adapter = RankingAdapter(requireContext())

        rankingFragmentViewModel.borders.observe(viewLifecycleOwner, Observer {
            (binding.rvRankingAllRankings.adapter as RankingAdapter).setBorder(it)

            rankingFragmentViewModel.users.observe(viewLifecycleOwner, Observer {
                val borderList = rankingFragmentViewModel.borders.value
                val userInfo = it[0]
                binding.cvRankingProfileImgBorder.backgroundTintList = ColorStateList.valueOf(borderList!!.get(userInfo.currentBorder).color)
                binding.tvRankingProfileName.text = userInfo.name
                binding.tvRankingCoin.text = userInfo.totalCoin.toString()
                binding.tvRankingMyRank.text = "# " + userInfo.ranking.toString()
                Glide.with(this@RankingFragment).load(userInfo.image).into(binding.ivRankingProfileImg)
            })

            rankingFragmentViewModel.rankings.observe(viewLifecycleOwner, Observer {
                val borderList = rankingFragmentViewModel.borders.value
                val top1 = it[0]
                binding.cvRankingTop1Border.backgroundTintList = ColorStateList.valueOf(borderList!!.get(top1.currentBorder).color)
                binding.tvRankingTop1Profile.text = top1.userName
                binding.tvRankingTop1Coin.text = top1.totalCoin.toString()
                Glide.with(this@RankingFragment).load(top1.image).into(binding.ivRankingTop1)
                if (it.size >= 2) {
                    val top2 = it[1]
                    binding.tvRankingTop2Profile.text = top2.userName
                    binding.tvRankingTop2Coin.text = top2.totalCoin.toString()
                    binding.cvRankingTop2Border.backgroundTintList = ColorStateList.valueOf(borderList!!.get(top2.currentBorder).color)
                    Glide.with(this@RankingFragment).load(top2.image).into(binding.ivRankingTop2)
                }
                if (it.size >= 3) {
                    val top3 = it[2]
                    binding.tvRankingTop3Profile.text = top3.userName
                    binding.tvRankingTop3Coin.text = top3.totalCoin.toString()
                    binding.cvRankingTop3Border.backgroundTintList = ColorStateList.valueOf(borderList!!.get(top3.currentBorder).color)
                    Glide.with(this@RankingFragment).load(top3.image).into(binding.ivRankingTop3)
                }
                if(it.size >= 4)
                    (binding.rvRankingAllRankings.adapter as RankingAdapter).setData(it.subList(3, it.size))
                else
                    (binding.rvRankingAllRankings.adapter as RankingAdapter).setData(listOf())
            })

        })




        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}