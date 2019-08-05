package com.appstreet.airtelassignment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.appstreet.home.api.HomeApi
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class HomeTest {


    @get:Rule
    val rule = InstantTaskExecutorRule()
    private var apiService = providesRetrofit()
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `network request`() {
        val result = apiService.getData("java", "since")
        print(result)
    }

    private fun providesRetrofit(): HomeApi {
        return Retrofit.Builder()
            .baseUrl("https://github-trending-api.now.sh/")
            .client(OkHttpClient.Builder().build())
            .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(HomeApi::class.java)
    }
}
