package com.example.klab2challenge.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.klab2challenge.databinding.FragmentHomeBinding
import com.example.klab2challenge.ui.challenge.ChallengeDetailActivity
import com.example.klab2challenge.ui.challenge.AddChallengeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeFragmentViewModel : HomeFragmentViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        //set recyclerviews and viewmodels
        binding.rvHomePopular.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val popularAdapter = HCPAdapter(requireContext())
        popularAdapter.itemClickListener = object : HCPAdapter.OnItemClickListener {
            override fun onItemClicked(challengeId : Int) {
                val i = Intent(requireContext(), ChallengeDetailActivity::class.java)
                i.putExtra("challengeId", challengeId)
                startActivity(i)
            }
        }
        binding.rvHomePopular.adapter = popularAdapter
        homeFragmentViewModel.popularChallenges.observe(viewLifecycleOwner, Observer {
            (binding.rvHomePopular.adapter as HCPAdapter).setData(it)
        })

        binding.rvHomeOfficial.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val officialAdapter = HCPAdapter(requireContext())
        officialAdapter.itemClickListener = object : HCPAdapter.OnItemClickListener {
            override fun onItemClicked(challengeId : Int) {
                val i = Intent(requireContext(), ChallengeDetailActivity::class.java)
                i.putExtra("challengeId", challengeId)
                startActivity(i)
            }
        }
        binding.rvHomeOfficial.adapter = officialAdapter
        homeFragmentViewModel.officialChallenges.observe(viewLifecycleOwner, Observer {
            (binding.rvHomeOfficial.adapter as HCPAdapter).setData(it)
        })

        binding.rvHomeUser.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val userAdapter = HCPAdapter(requireContext())
        userAdapter.itemClickListener = object : HCPAdapter.OnItemClickListener {
            override fun onItemClicked(challengeId : Int) {
                val i = Intent(requireContext(), ChallengeDetailActivity::class.java)
                i.putExtra("challengeId", challengeId)
                startActivity(i)
            }
        }
        binding.rvHomeUser.adapter = userAdapter
        homeFragmentViewModel.userChallenges.observe(viewLifecycleOwner, Observer {
            (binding.rvHomeUser.adapter as HCPAdapter).setData(it)
        })



        binding.fabHomeAdd.setOnClickListener {
            val i = Intent(requireContext(), AddChallengeActivity::class.java)
            startActivity(i)
        }



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}