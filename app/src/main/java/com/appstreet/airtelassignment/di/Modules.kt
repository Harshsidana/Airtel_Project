package com.appstreet.airtelassignment.di

import com.appstreet.base.retrofit.NetworkBuilder
import com.appstreet.home.api.HomeApi
import com.appstreet.home.repository.HomeRepository
import com.appstreet.home.repository.HomeRepositoryImpl
import com.appstreet.home.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

object Modules {
    private val viewModelModules = module {
        viewModel { HomeViewModel(get(), get()) }


    }

    private val networkModules = module {
        single<HomeApi> { NetworkBuilder.create(NetworkBuilder.BASE_URL, HomeApi::class.java, get()) }
    }

    private val repoModules = module {
        single<HomeRepository> { HomeRepositoryImpl(get()) }
    }


    fun getAll() = listOf(
        viewModelModules,
        networkModules,
        repoModules

    )
}