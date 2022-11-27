package com.example.coreui.di

import com.example.coreui.SampleStateViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val coreUiModule = module {
	viewModel { SampleStateViewModel(get()) }
}