package com.example.featureauth.di

import com.example.featureauth.SampleStateViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiAuthModule = module{
	viewModel {
		SampleStateViewModel(get())
	}
}