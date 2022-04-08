package com.example.studyapplication

import android.app.Application
import com.example.coreui.di.coreUiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication: Application() {

	override fun onCreate() {
		super.onCreate()
		startKoin {
			androidLogger()
			androidContext(this@MainApplication)
			modules(
				coreUiModule
			)
		}
	}
}