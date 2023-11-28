package com.example.klab2challenge.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.klab2challenge.databinding.FragmentHomeBinding
import com.example.klab2challenge.db.ChallengeDatabase
import com.example.klab2challenge.retrofit.RetrofitInterface
import com.example.klab2challenge.ui.challenge.ChallengeDetailActivity
import com.example.klab2challenge.ui.challenge.AddChallengeActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: ChallengeDatabase
    private lateinit var retrofit: RetrofitInterface

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
        val popularAdapter = ChallengeAdapter(requireContext(), popularViewModel.itemList.value!!)
        popularAdapter.itemClickListener = object : ChallengeAdapter.OnItemClickListener {
            override fun onItemClicked(challengeId : Int) {
                val i = Intent(requireContext(), ChallengeDetailActivity::class.java)
                i.putExtra("challengeId", challengeId)
                startActivity(i)
            }
        }
        binding.rvHomePopular.adapter = popularAdapter
        popularViewModel.itemList.observe(viewLifecycleOwner, Observer {
            (binding.rvHomePopular.adapter as ChallengeAdapter).setData(it)
        })

        binding.rvHomeOfficial.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val officialAdapter = ChallengeAdapter(requireContext(), officialViewModel.itemList.value!!)
        officialAdapter.itemClickListener = object : ChallengeAdapter.OnItemClickListener {
            override fun onItemClicked(challengeId : Int) {
                val i = Intent(requireContext(), ChallengeDetailActivity::class.java)
                i.putExtra("challengeId", challengeId)
                startActivity(i)
            }
        }
        binding.rvHomeOfficial.adapter = officialAdapter
        officialViewModel.itemList.observe(viewLifecycleOwner, Observer {
            (binding.rvHomeOfficial.adapter as ChallengeAdapter).setData(it)
        })

        binding.rvHomeUser.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val userAdapter = ChallengeAdapter(requireContext(), userViewModel.itemList.value!!)
        userAdapter.itemClickListener = object : ChallengeAdapter.OnItemClickListener {
            override fun onItemClicked(challengeId : Int) {
                val i = Intent(requireContext(), ChallengeDetailActivity::class.java)
                i.putExtra("challengeId", challengeId)
                startActivity(i)
            }
        }
        binding.rvHomeUser.adapter = userAdapter
        userViewModel.itemList.observe(viewLifecycleOwner, Observer {
            (binding.rvHomeUser.adapter as ChallengeAdapter).setData(it)
        })


        db = ChallengeDatabase.getInstance(requireContext())
        val hcpDao = db.getHCPDAO()
        CoroutineScope(Dispatchers.IO).launch {
            popularViewModel.setChallenges(hcpDao.getPopularHCPs())
            userViewModel.setChallenges(hcpDao.getUserHCPs())
            popularViewModel.setChallenges(hcpDao.getOfficialHCPs())
        }


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