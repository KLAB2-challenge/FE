package com.example.klab2challenge.ui.mypage

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.getColor
import androidx.core.content.ContextCompat.getDrawable
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.example.klab2challenge.MainActivity
import com.example.klab2challenge.R
import com.example.klab2challenge.databinding.FragmentMyPageBinding
import com.example.klab2challenge.retrofit.GetMemberInfosRequest
import com.example.klab2challenge.retrofit.GetMemberInfosResponse
import com.example.klab2challenge.retrofit.RetrofitUtil
import com.example.klab2challenge.retrofit.getUserBorder
import com.example.klab2challenge.retrofit.getUserCoin
import com.example.klab2challenge.retrofit.getUserName
import com.example.klab2challenge.retrofit.getUserProfileUrl
import com.example.klab2challenge.retrofit.getUserTotalCoin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPageFragment : Fragment() {

    private var _binding: FragmentMyPageBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(MyPageViewModel::class.java)

        _binding = FragmentMyPageBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.tvMypageChooseDesign.setOnClickListener {
            val i = Intent(requireContext(), BorderActivity::class.java)
            startActivity(i)
        }

        binding.tvMypageUserName.text = getUserName(requireContext())
        Log.d("hyunheemypageborder", getUserBorder(requireContext()).toString())
        binding.ifbMypageProfileBorder.backgroundTintList = ColorStateList.valueOf(getUserBorder(requireContext()))
        binding.tvMypageAllCoin.text = getUserTotalCoin(requireContext()).toString()
        binding.tvMypageMyCoin.text = getUserCoin(requireContext()).toString()
        Glide.with(this).load(getUserProfileUrl(requireContext())).into(binding.ivMypageProfile)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}