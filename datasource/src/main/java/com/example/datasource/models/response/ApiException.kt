package com.example.datasource.models.response

import com.google.gson.Gson
import java.io.Reader

data class ApiException(
	val errorResponse: ErrorResponse?,
) : Throwable(errorResponse?.message)

//todo move to json Extension
inline fun <reified T> Reader.fromJson(): T {
	return Gson().fromJson(this, T::class.java)
}