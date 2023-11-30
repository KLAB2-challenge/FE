package com.example.klab2challenge.ui.mychallenge

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.klab2challenge.ChallengeViewModel
import com.example.klab2challenge.databinding.FragmentMyChallengeBinding
import com.example.klab2challenge.ui.challenge.AddChallengeActivity
import com.example.klab2challenge.ui.challenge.ChallengeDetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyChallengeFragment : Fragment() {

    //challenge 선택 시 이동 구현? -> adapter에 onclick listener
    //challenge add 시 업데이트 반영? roomdb 추가는 될텐데... 어떻게 새로고침하지?
    //특히 home, mychallenge가 각각 서로 다른 화면에서 challenge add 시 새로고침?

    private var _binding: FragmentMyChallengeBinding? = null
    private val binding get() = _binding!!


    private val myChallengeFragmentViewModel : MyChallengeFragmentViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyChallengeBinding.inflate(inflater, container, false)
        val root: View = binding.root



        binding.rvMyChallenge.layoutManager = GridLayoutManager(requireContext(), 3)
        val adapter = MCPAdapter()
        adapter.itemClickListener = object : MCPAdapter.OnItemClickListener {
            override fun onItemClicked(challengeId: Int) {
                val i = Intent(requireContext(), ChallengeDetailActivity::class.java)
                i.putExtra("challengeId", challengeId)
                startActivity(i)
            }
        }
        binding.rvMyChallenge.adapter = MCPAdapter()
        myChallengeFragmentViewModel.myChallenges.observe(viewLifecycleOwner, Observer {
            (binding.rvMyChallenge.adapter as MCPAdapter).setData(it)
        })



        binding.fabMychallengeAdd.setOnClickListener {
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