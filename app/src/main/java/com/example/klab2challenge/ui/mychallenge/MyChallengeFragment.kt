package com.example.klab2challenge.ui.mychallenge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.klab2challenge.databinding.FragmentMyChallengeBinding
import com.example.klab2challenge.ui.home.ChallengeAdapter

class MyChallengeFragment : Fragment() {
    private var _binding: FragmentMyChallengeBinding? = null
    private val binding get() = _binding!!
    private val myChallenges = MyChallengeViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyChallengeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.rvMyChallenge.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rvMyChallenge.adapter = MyChallengeAdapter(myChallenges.itemList.value!!)
        myChallenges.itemList.observe(viewLifecycleOwner, Observer {
            (binding.rvMyChallenge.adapter as MyChallengeAdapter).setData(it)
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}