package com.example.klab2challenge.ui.mypage

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.klab2challenge.databinding.FragmentMyPageBinding
import com.example.klab2challenge.db.MyDatabase
import com.example.klab2challenge.retrofit.RetrofitInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyPageFragment : Fragment() {

    //border activity 갔다 온 후, 코인 새로고침 어떻게 할 것인지? 새로고침 이슈는 startactivityforresult? or 생명주기?

    private var _binding: FragmentMyPageBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyPageBinding.inflate(inflater, container, false)
        val root: View = binding.root



////        db = MyDatabase.getInstance(requireContext())
//        val userDao = db.getUserDAO()
//        val borderDao = db.getBorderDAO()
//
//        CoroutineScope(Dispatchers.IO).launch {
//            val userEntity = userDao.getUser()[0]
//            val borderList = borderDao.getAllBorders()
//
//            binding.tvMypageUserName.text = userEntity.name
//            binding.ifbMypageProfileBorder.backgroundTintList = ColorStateList.valueOf(borderList[userEntity.currentBorder].color)
//            binding.tvMypageAllCoin.text = userEntity.totalCoin.toString()
//            binding.tvMypageMyCoin.text = userEntity.currentCoin.toString()
//            Glide.with(this@MyPageFragment).load(userEntity.image).into(binding.ivMypageProfile)
//        }



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