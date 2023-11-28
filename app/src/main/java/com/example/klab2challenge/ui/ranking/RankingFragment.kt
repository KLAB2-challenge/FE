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
import com.example.klab2challenge.db.ChallengeDatabase
import com.example.klab2challenge.retrofit.RetrofitInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RankingFragment : Fragment() {

    private var _binding: FragmentRankingBinding? = null
    private val binding get() = _binding!!

    private lateinit var db: ChallengeDatabase
    private lateinit var retrofit: RetrofitInterface

    var rankingViewModel = RankingViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRankingBinding.inflate(inflater, container, false)
        val root: View = binding.root



        db = ChallengeDatabase.getInstance(requireContext())
        val rankingDao = db.getRankingDAO()
        val borderDao = db.getBorderDAO()
        val userDao = db.getUserDAO()

        CoroutineScope(Dispatchers.IO).launch {
            val borderList = borderDao.getAllBorders()
            binding.rvRankingAllRankings.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            binding.rvRankingAllRankings.adapter =
                RankingAdapter(requireContext(), rankingViewModel.itemList.value!!, borderList)
            rankingViewModel.itemList.observe(viewLifecycleOwner, Observer {
                (binding.rvRankingAllRankings.adapter as RankingAdapter).setData(it)
            })

            val userEntity = userDao.getUser()[0]
            Glide.with(this@RankingFragment).load(userEntity.image).into(binding.ivRankingProfileImg)
            binding.tvRankingProfileName.text = userEntity.userName
            binding.cvRankingProfileImgBorder.backgroundTintList = ColorStateList.valueOf(userEntity.currentBorder)

            val rankingList = rankingDao.getRanking()
            val top1 = rankingList[0]
            binding.cvRankingTop1Border.backgroundTintList = ColorStateList.valueOf(borderList[top1.currentBorder].color)
            binding.tvRankingTop1Profile.text = top1.userName
            binding.tvRankingTop1Coin.text = top1.totalCoin.toString()
            binding.cvRankingTop1Border.backgroundTintList = ColorStateList.valueOf(borderList[top1.currentBorder].color)
            Glide.with(this@RankingFragment).load(top1.image).into(binding.ivRankingTop1)
            if (rankingList.size >= 2) {
                val top2 = rankingList[1]
                binding.tvRankingTop2Profile.text = top2.userName
                binding.tvRankingTop2Coin.text = top2.totalCoin.toString()
                binding.cvRankingTop2Border.backgroundTintList = ColorStateList.valueOf(borderList[top2.ranking].color)
                Glide.with(this@RankingFragment).load(top2.image).into(binding.ivRankingTop2)
            }
            if (rankingList.size >= 3) {
                val top3 = rankingList[2]
                binding.tvRankingTop3Profile.text = top3.userName
                binding.tvRankingTop3Coin.text = top3.totalCoin.toString()
                binding.cvRankingTop3Border.backgroundTintList = ColorStateList.valueOf(borderList[top3.ranking].color)
                Glide.with(this@RankingFragment).load(top3.image).into(binding.ivRankingTop3)
            }
            if(rankingList.size >= 4)
                rankingViewModel.setItem(rankingList.subList(3, rankingList.size))
        }



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}