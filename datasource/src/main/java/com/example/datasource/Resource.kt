package com.example.datasource


sealed class Resource<T>(val data: T?) {

	class Loading<T>(data: T? = null) : Resource<T>(data)
	class Success<T>(data: T?) : Resource<T>(data)
	class Error<T>(val throwable: Throwable? = null, data: T? = null) : Resource<T>(data)

	fun isSuccess() = this is Success
	fun isError() = this is Error
	fun isLoading() = this is Loading

	companion object {
		fun <T> success(data: T?) = Success(data)
		fun <T> error(throwable: Throwable? = null, data: T? = null) = Error(throwable, data)
		fun <T> loading(data: T? = null) = Loading(data)
	}
}