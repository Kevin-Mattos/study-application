package com.example.datasource

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

data class ClientInfo(
	var writeTimeout: Long? = null,
	var readTimeout: Long? = null,
	var interceptors: Set<Interceptor>? = null
)

fun createClient(block: ClientInfo.() -> Unit): OkHttpClient {
	val clientInfo = ClientInfo()
	clientInfo.apply(block)

	return OkHttpClient.Builder().apply {
		clientInfo.writeTimeout?.let { writeTimeout(it, TimeUnit.SECONDS) }
		clientInfo.readTimeout?.let { readTimeout(it, TimeUnit.SECONDS) }
		clientInfo.interceptors?.forEach {
			addInterceptor(it)
		}
	}.build()
}

fun defaultInterceptors(level: HttpLoggingInterceptor.Level): Set<HttpLoggingInterceptor> {
	return setOf(createLoggingInterceptor(level))
}

fun createLoggingInterceptor(level: HttpLoggingInterceptor.Level): HttpLoggingInterceptor {
	return HttpLoggingInterceptor().apply { this.level = level }
}