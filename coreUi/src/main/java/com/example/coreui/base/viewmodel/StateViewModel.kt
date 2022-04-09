package com.example.coreui.base.viewmodel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class StateViewModel<O> : BaseViewModel() {

	open val initialState: Resource<O> = Resource.loading()

	protected val _stateLiveData: MutableStateFlow<Resource<O>> by lazy {
		MutableStateFlow(initialState).apply {
			launchIO {
				if (initialState.isLoading()) {
					loadValue()
				}
			}
		}
	}
	val stateLiveData: StateFlow<Resource<O>> get() = _stateLiveData


	protected open suspend fun tryAgain() = _stateLiveData.apply {
		//todo should emit loading, but not initial
		emit(initialState)
		loadValue()
	}

	private suspend fun MutableStateFlow<Resource<O>>.loadValue() {
		emit(fetchValue())
	}

	abstract suspend fun fetchValue(): Resource<O>
}


sealed class Resource<T>(val data: T?) {

	class Loading<T>(data: T? = null) : Resource<T>(data)
	class Success<T>(data: T) : Resource<T>(data)
	class Error<T>(data: T? = null, val throwable: Throwable? = null) : Resource<T>(data)

	fun isSuccess() = this is Success
	fun isError() = this is Error
	fun isLoading() = this is Loading

	companion object {
		fun <T> success(data: T) = Success(data)
		fun <T> error(data: T? = null, throwable: Throwable? = null) = Error(data, throwable)
		fun <T> loading(data: T? = null) = Loading(data)
	}
}