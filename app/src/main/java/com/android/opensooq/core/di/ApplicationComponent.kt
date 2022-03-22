package com.android.opensooq.core.di

import com.android.opensooq.AndroidApplication
import com.android.opensooq.features.fragments.HomeFragment
import com.android.opensooq.core.api.ApiModule
import com.android.opensooq.core.di.viewmodel.ViewModelModule
import com.android.opensooq.features.fragments.CityDetailFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(application: AndroidApplication)
    fun inject(homeFragment: HomeFragment)
    fun inject(cityDetailFragment: CityDetailFragment)
}
