package com.example.klab2challenge.ui.mychallenge

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.klab2challenge.databinding.FragmentMyChallengeBinding
import com.example.klab2challenge.retrofit.GetAllCommentsResponse
import com.example.klab2challenge.retrofit.GetChallengeResponse
import com.example.klab2challenge.retrofit.GetMemberAllChallengesRequest
import com.example.klab2challenge.retrofit.GetMemberAllChallengesResponse
import com.example.klab2challenge.retrofit.RetrofitUtil
import com.example.klab2challenge.retrofit.getUserName
import com.example.klab2challenge.ui.challenge.AddChallengeActivity
import com.example.klab2challenge.ui.home.ChallengeAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        RetrofitUtil.getRetrofitUtil()
            .getChallenge(GetMemberAllChallengesRequest(getUserName(requireContext()), 0, 10))
            .enqueue(object : Callback<GetMemberAllChallengesResponse> {
                override fun onResponse(
                    call: Call<GetMemberAllChallengesResponse>,
                    response: Response<GetMemberAllChallengesResponse>
                ) {
                    if (response.isSuccessful) {
                        val list = ArrayList<GetChallengeResponse>()
                        list.addAll(response.body()!!.challenges)
                        myChallenges.setMyChallenge(list)
                    } else {
                        Log.d("hyunheemyc", response.errorBody().toString())
                    }
                }

                override fun onFailure(call: Call<GetMemberAllChallengesResponse>, t: Throwable) {
                    Log.d("hyunheemyc", t.message.toString())
                }

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