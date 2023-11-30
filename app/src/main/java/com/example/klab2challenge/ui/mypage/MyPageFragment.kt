package com.example.klab2challenge.ui.mypage

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.klab2challenge.BorderViewModel
import com.example.klab2challenge.UserViewModel
import com.example.klab2challenge.databinding.FragmentMyPageBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyPageFragment : Fragment() {

    //border activity 갔다 온 후, 코인 새로고침 어떻게 할 것인지? 새로고침 이슈는 startactivityforresult? or 생명주기?

    private var _binding: FragmentMyPageBinding? = null
    private val binding get() = _binding!!
    private val borderViewModel : BorderViewModel by viewModel()
    private val userViewModel : UserViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyPageBinding.inflate(inflater, container, false)
        val root: View = binding.root

        borderViewModel.borders.observe(viewLifecycleOwner, Observer {

        })
        userViewModel.users.observe(viewLifecycleOwner, Observer {
            val userInfo = it.get(0)
            val borderList = borderViewModel.borders.value
            Log.d("mypageUserInfo", userInfo.toString())
            Log.d("mypageborderList", borderList.toString())
            binding.tvMypageUserName.text = userInfo.name
            binding.ifbMypageProfileBorder.backgroundTintList = ColorStateList.valueOf(borderList!!.get(userInfo.currentBorder).color)
            binding.tvMypageAllCoin.text = userInfo.totalCoin.toString()
            binding.tvMypageMyCoin.text = userInfo.currentCoin.toString()
            Glide.with(this@MyPageFragment).load(userInfo.image).into(binding.ivMypageProfile)
        })



        binding.tvMypageChooseDesign.setOnClickListener {
            val i = Intent(requireContext(), BorderActivity::class.java)
            startActivity(i)
        }



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}