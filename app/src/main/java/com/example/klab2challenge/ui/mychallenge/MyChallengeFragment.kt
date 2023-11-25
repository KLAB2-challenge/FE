package com.example.klab2challenge.ui.mychallenge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.klab2challenge.databinding.FragmentMyChallengeBinding
import com.example.klab2challenge.ui.home.ChallengeAdapter
import com.example.klab2challenge.ui.home.ChallengeViewModel

class MyChallengeFragment : Fragment() {
    private var _binding: FragmentMyChallengeBinding? = null
    private val binding get() = _binding!!
    private var myChallengeViewModel = ChallengeViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(MyChallengeViewModel::class.java)

        _binding = FragmentMyChallengeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.rvMyChallenge.layoutManager = GridLayoutManager(requireContext(), 3)
        val adapter = ChallengeAdapter(myChallengeViewModel.itemList.value!!)
        dashboardViewModel.itemList.observe(viewLifecycleOwner, Observer {
            adapter.setData(myChallengeViewModel.itemList.value!!)
        })
        binding.rvMyChallenge.adapter = adapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}