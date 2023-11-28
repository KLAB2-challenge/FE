package com.example.klab2challenge.ui.mychallenge

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.klab2challenge.databinding.FragmentMyChallengeBinding
import com.example.klab2challenge.db.ChallengeDatabase
import com.example.klab2challenge.retrofit.RetrofitInterface
import com.example.klab2challenge.ui.challenge.AddChallengeActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyChallengeFragment : Fragment() {
    private var _binding: FragmentMyChallengeBinding? = null
    private val binding get() = _binding!!

    private lateinit var db: ChallengeDatabase
    private lateinit var retrofit: RetrofitInterface

    private val myChallengeViewModel = MyChallengeViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyChallengeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.rvMyChallenge.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rvMyChallenge.adapter = MyChallengeAdapter(myChallengeViewModel.itemList.value!!)
        myChallengeViewModel.itemList.observe(viewLifecycleOwner, Observer {
            (binding.rvMyChallenge.adapter as MyChallengeAdapter).setData(it)
        })



        db = ChallengeDatabase.getInstance(requireContext())
        val mcpDao = db.getMCPDAO()
        CoroutineScope(Dispatchers.IO).launch {
            myChallengeViewModel.setMyChallenge(mcpDao.getMCPs())
        }



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