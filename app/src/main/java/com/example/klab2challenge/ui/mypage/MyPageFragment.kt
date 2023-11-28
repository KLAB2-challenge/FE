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
import androidx.annotation.RestrictTo.Scope
import androidx.core.content.ContextCompat.getColor
import androidx.core.content.ContextCompat.getDrawable
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.example.klab2challenge.MainActivity
import com.example.klab2challenge.R
import com.example.klab2challenge.databinding.FragmentMyPageBinding
import com.example.klab2challenge.db.ChallengeDatabase
import com.example.klab2challenge.retrofit.GetMemberInfosRequest
import com.example.klab2challenge.retrofit.GetMemberInfosResponse
import com.example.klab2challenge.retrofit.RetrofitInterface
import com.example.klab2challenge.retrofit.RetrofitUtil
import com.example.klab2challenge.retrofit.getUserBorder
import com.example.klab2challenge.retrofit.getUserCoin
import com.example.klab2challenge.retrofit.getUserName
import com.example.klab2challenge.retrofit.getUserProfileUrl
import com.example.klab2challenge.retrofit.getUserTotalCoin
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPageFragment : Fragment() {

    private var _binding: FragmentMyPageBinding? = null
    private val binding get() = _binding!!

    private lateinit var db: ChallengeDatabase
    private lateinit var retrofit: RetrofitInterface

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyPageBinding.inflate(inflater, container, false)
        val root: View = binding.root



        db = ChallengeDatabase.getInstance(requireContext())
        val userDao = db.getUserDAO()
        val borderDao = db.getBorderDAO()

        CoroutineScope(Dispatchers.IO).launch {
            val userEntity = userDao.getUser()[0]
            val borderList = borderDao.getAllBorders()

            binding.tvMypageUserName.text = userEntity.userName
            binding.ifbMypageProfileBorder.backgroundTintList = ColorStateList.valueOf(borderList[userEntity.currentBorder].color)
            binding.tvMypageAllCoin.text = userEntity.totalCoin.toString()
            binding.tvMypageMyCoin.text = userEntity.currentCoin.toString()
            Glide.with(this@MyPageFragment).load(userEntity.image).into(binding.ivMypageProfile)
        }



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