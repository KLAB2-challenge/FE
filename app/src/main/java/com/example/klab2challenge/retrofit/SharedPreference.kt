package com.example.klab2challenge.retrofit

import android.content.Context
import androidx.appcompat.app.AppCompatActivity

fun saveUserName(context: Context, userName: String) {
    val pref =
        context.getSharedPreferences("userName_spf", Context.MODE_PRIVATE) //shared key 설정
    val edit = pref.edit() // 수정모드
    edit.putString("userName_spf", userName) // 값 넣기
    edit.apply() // 적용하기
}
fun getUserName(context: Context): String {
    val spf = context.getSharedPreferences("userName_spf", AppCompatActivity.MODE_PRIVATE)
    return spf.getString("userName_spf", 1.toString())!!
}


