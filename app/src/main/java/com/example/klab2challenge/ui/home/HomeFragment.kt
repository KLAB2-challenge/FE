package com.example.klab2challenge.ui.home

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
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
import com.example.klab2challenge.ui.challenge.ChallengeDetailActivity

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