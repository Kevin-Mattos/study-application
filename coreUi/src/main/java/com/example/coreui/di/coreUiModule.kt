package com.example.coreui.di

import com.example.coreui.SampleFragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val coreUiModule = module {
	viewModel { SampleFragment.SampleStateViewModel(get()) }
}