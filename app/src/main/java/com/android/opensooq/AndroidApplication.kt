package com.android.opensooq

import android.app.Application
import android.content.Context
import com.android.opensooq.core.di.ApplicationComponent
import com.android.opensooq.core.di.DaggerApplicationComponent
import com.android.opensooq.core.api.ApiEndPoints
import com.android.opensooq.core.api.ApiModule

class AndroidApplication : Application() {

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {

        DaggerApplicationComponent
            .builder()
            .apiModule(ApiModule(ApiEndPoints.BASE_URL))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        this.injectMembers()
        context = applicationContext
    }

    private fun injectMembers() = appComponent.inject(this)

    fun getApplicationComponent(): ApplicationComponent {
        return appComponent
    }
    companion object {
       lateinit var context: Context
    }
}
