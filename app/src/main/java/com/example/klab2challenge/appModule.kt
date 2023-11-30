package com.example.klab2challenge

import com.example.klab2challenge.db.repository.BorderRepository
import com.example.klab2challenge.db.repository.ChallengeRepository
import com.example.klab2challenge.db.MyDatabase
import com.example.klab2challenge.db.repository.RankingRepository
import com.example.klab2challenge.db.repository.UserRepository
import com.example.klab2challenge.retrofit.RetrofitInterface
import com.example.klab2challenge.ui.home.HomeFragmentViewModel
import com.example.klab2challenge.ui.mychallenge.MyChallengeFragmentViewModel
import com.example.klab2challenge.ui.mypage.MyPageFragmentViewModel
import com.example.klab2challenge.ui.ranking.RankingFragmentViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val BASE_URL = BuildConfig.BASE_URL

val appModule = module {
    val applicationScope = CoroutineScope(SupervisorJob())

    single<RetrofitInterface> {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(RetrofitInterface::class.java)
    }
    single {
        MyDatabase.getInstance(androidApplication(), applicationScope)
    }
    single {
        ChallengeRepository(get(), get()).apply {
            applicationScope.launch {
                init()
            }
        }
    }
    single {
        RankingRepository(get(), get()).apply {
            applicationScope.launch {
                init()
            }
        }
    }
    single {
        UserRepository(get(), get()).apply {
            applicationScope.launch {
                init()
            }
        }
    }
    single {
        BorderRepository(get(), get()).apply {
            applicationScope.launch {
                init(androidContext())
            }
        }
    }
    single {
        get<MyDatabase>().getChallengeDAO()
    }
    single {
        get<MyDatabase>().getRankingDAO()
    }
    single {
        get<MyDatabase>().getUserDAO()
    }
    single {
        get<MyDatabase>().getBorderDAO()
    }
    viewModel {
        ChallengeViewModel(get())
    }
    viewModel {
        RankingViewModel(get())
    }
    viewModel {
        UserViewModel(get())
    }
    viewModel {
        BorderViewModel(get())
    }
    viewModel {
        MainActivityViewModel(get(), get(), get(), get())
    }
    viewModel {
        RankingFragmentViewModel(get(), get(), get())
    }
    viewModel {
        MyChallengeFragmentViewModel(get())
    }
    viewModel {
        HomeFragmentViewModel(get())
    }
    viewModel {
        MyPageFragmentViewModel(get(), get())
    }
}