package com.example.auth.di

import com.example.auth.services.SampleCepService
import com.example.datasource.createService
import org.koin.dsl.module

val dataAuthModule = module{
	factory<SampleCepService> { createService(get()) }
}