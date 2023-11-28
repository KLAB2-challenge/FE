package com.example.klab2challenge

import com.example.klab2challenge.db.ChallengeRepository
import com.example.klab2challenge.db.MyDatabase
import com.example.klab2challenge.retrofit.RetrofitUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    val applicationScope = CoroutineScope(SupervisorJob())

    single {
        MyDatabase.getInstance(androidApplication(), applicationScope)
    }
    single {
        RetrofitUtil.getRetrofitUtil()
    }
    single {
        ChallengeRepository(get(), get())
    }
    single {
        get<MyDatabase>().getHCPDAO()
    }
    viewModel {
        ChallengeViewModel(get())
    }
}