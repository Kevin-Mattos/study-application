package com.example.studyapplication

import android.app.Application
import com.example.auth.di.dataAuthModule
import com.example.auth.di.domainAuthModule
import com.example.coreui.di.coreUiModule
import com.example.datasource.dataSourceModule
import com.example.featureauth.di.uiAuthModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@MainApplication)
            modules(
                coreUiModule,
                dataSourceModule,
                dataAuthModule,
                domainAuthModule,
                uiAuthModule
            )
        }
    }
}
