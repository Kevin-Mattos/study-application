package com.example.datasource

import com.example.datasource.adapter.CallAdapterFactory
import com.example.datasource.environment.Environment
import com.example.datasource.services.SampleCepService
import com.google.gson.Gson
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataSourceModule = module {
	single { defaultRetrofit() }

	factory<SampleCepService> { createService(get()) }
}

fun defaultRetrofit(): Retrofit {
	val url = Environment.Env.url
	val client = createClient {
		writeTimeout = 30L
		readTimeout = 30L
		interceptors = defaultInterceptors(HttpLoggingInterceptor.Level.BODY)
	}

	return Retrofit.Builder().apply {
		addConverterFactory(GsonConverterFactory.create(Gson()))
		addCallAdapterFactory(CallAdapterFactory())
		client(client)
		baseUrl(url)
	}.build()
}

inline fun <reified T> createService(retrofit: Retrofit): T {
	return retrofit.create(T::class.java)
}