package com.example.klab2challenge.ui.ranking

import android.os.Bundle
import android.service.notification.NotificationListenerService.Ranking
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.klab2challenge.databinding.FragmentRankingBinding

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

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}