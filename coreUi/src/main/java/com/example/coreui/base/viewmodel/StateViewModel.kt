package com.example.coreui.base.viewmodel

import com.example.datasource.Resource
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
