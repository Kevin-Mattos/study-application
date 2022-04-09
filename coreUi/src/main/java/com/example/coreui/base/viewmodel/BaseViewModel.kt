package com.example.coreui.base.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

abstract class BaseViewModel : ViewModel() {

	fun main() = Dispatchers.Main
	fun io() = Dispatchers.IO
	fun computing() = Dispatchers.Default

	protected fun launch(
		dispatcher: CoroutineDispatcher = Dispatchers.Main,
		coroutineStart: CoroutineStart = CoroutineStart.DEFAULT,
		block: suspend CoroutineScope.() -> Unit
	) = viewModelScope.launch(dispatcher, coroutineStart, block)

	protected fun launchMain(block: suspend CoroutineScope.() -> Unit) =
		launch(main(), block = block)

	protected fun launchIO(block: suspend CoroutineScope.() -> Unit) = launch(io(), block = block)

	protected fun launchComputing(block: suspend CoroutineScope.() -> Unit) =
		launch(computing(), block = block)
}