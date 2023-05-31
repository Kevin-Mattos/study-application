package com.example.coreui.base.viewmodel

import com.example.coreui.base.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class StateViewModel<O> : BaseViewModel() {

	open val initialState: Resource<O> = Resource.loading()
	protected var lastFetchedValue: O? = null

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


	open fun tryAgain() = launchIO {
		_stateLiveData.apply {
			emit(Resource.loading(lastFetchedValue))
			loadValue()
		}
	}

	private suspend fun MutableStateFlow<Resource<O>>.loadValue() {
		fetchValue().apply {
			if(isSuccess()) {
				lastFetchedValue = data
			}
			emit(this)
		}
	}

	abstract suspend fun fetchValue(): Resource<O>
}
