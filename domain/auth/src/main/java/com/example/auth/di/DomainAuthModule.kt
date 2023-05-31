package com.example.auth.di

import com.example.auth.FetchCepUseCase
import org.koin.dsl.module

val domainAuthModule = module {
	factory { FetchCepUseCase(get()) }
}