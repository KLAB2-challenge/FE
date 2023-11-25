package com.example.klab2challenge.ui.mypage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.klab2challenge.R
import com.example.klab2challenge.databinding.FragmentMyPageBinding
import com.example.klab2challenge.retrofit.GetMemberInfosRequest
import com.example.klab2challenge.retrofit.GetMemberInfosResponse
import com.example.klab2challenge.retrofit.RetrofitUtil
import com.example.klab2challenge.retrofit.getUserName
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPageFragment : Fragment() {

    private var _binding: FragmentMyPageBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var color : ArrayList<Int>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(MyPageViewModel::class.java)

        _binding = FragmentMyPageBinding.inflate(inflater, container, false)
        val root: View = binding.root

        color = arrayListOf(
            getColor(requireContext(), R.color.gold), getColor(requireContext(), R.color.green), getColor(requireContext(), R.color.cherry),
            getColor(requireContext(), R.color.blueberry), getColor(requireContext(), R.color.sunny), getColor(requireContext(), R.color.rainy)
        )


        binding.tvMypageChooseDesign.setOnClickListener {
            val i = Intent(requireContext(), BorderActivity::class.java)
            startActivity(i)
        }

        RetrofitUtil.getRetrofitUtil()
            .getMemberInfos(GetMemberInfosRequest(getUserName(requireContext()))).enqueue(object :
                Callback<GetMemberInfosResponse> {
                override fun onResponse(
                    call: Call<GetMemberInfosResponse>,
                    response: Response<GetMemberInfosResponse>
                ) {
                    if(response.isSuccessful) {
                        val data = response.body()!!
                        binding.tvMypageUserName.text = data.memberName
                        binding.ifbMypageProfileBorder.background = resources.getDrawable(color.get(data.infos.currentBorder))
                    } else {
                        Log.d("hyunheemp", response.errorBody().toString())
                    }
                }

                override fun onFailure(call: Call<GetMemberInfosResponse>, t: Throwable) {
                    Log.d("hyunheemp", t.message.toString())
                }

            })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}