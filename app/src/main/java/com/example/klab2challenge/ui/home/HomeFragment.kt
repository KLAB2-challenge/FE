package com.example.klab2challenge.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.klab2challenge.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
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
        binding.rvHomePopular.adapter = ChallengeAdapter(popularViewModel.itemList.value!!)
        popularViewModel.itemList.observe(viewLifecycleOwner, Observer {
            (binding.rvHomePopular.adapter as ChallengeAdapter).setData(it)
        })

        binding.rvHomeOfficial.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvHomeOfficial.adapter = ChallengeAdapter(officialViewModel.itemList.value!!)
        officialViewModel.itemList.observe(viewLifecycleOwner, Observer {
            (binding.rvHomeOfficial.adapter as ChallengeAdapter).setData(it)
        })

        binding.rvHomeUser.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvHomeUser.adapter = ChallengeAdapter(userViewModel.itemList.value!!)
        userViewModel.itemList.observe(viewLifecycleOwner, Observer {
            (binding.rvHomeUser.adapter as ChallengeAdapter).setData(it)
        })

        binding.ivHomeMore1.setOnClickListener {
            popularViewModel.addChallenge("1")
        }
        binding.tvHomeMore1.setOnClickListener {
            popularViewModel.deleteChallenge("1")
        }
        binding.ivHomeMore2.setOnClickListener {
            officialViewModel.addChallenge("1")
        }
        binding.tvHomeMore2.setOnClickListener {
            officialViewModel.deleteChallenge("1")
        }
        binding.ivHomeMore3.setOnClickListener {
            userViewModel.addChallenge("1")
        }
        binding.tvHomeMore3.setOnClickListener {
            userViewModel.deleteChallenge("1")
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}