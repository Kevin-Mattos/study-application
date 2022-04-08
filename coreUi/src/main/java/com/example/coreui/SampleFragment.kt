package com.example.coreui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.coreui.base.ComposeStateFragment
import com.example.coreui.base.viewmodel.Resource
import com.example.coreui.base.viewmodel.Resource.Companion.success
import com.example.coreui.base.viewmodel.StateViewModel
import kotlinx.coroutines.delay

class SampleFragment: ComposeStateFragment<Int>() {
	//TODO KOIN
	override val viewModel: SampleStateViewModel = SampleStateViewModel()

	@Composable
	override fun OnSuccess(data: Int?) {
		Text(text = "teste do kevinho $data")
	}

	@Composable
	override fun OnError(data: Int?, throwable: Throwable?) {
		Text(text = "ERRO $data")
	}

	class SampleStateViewModel: StateViewModel<Int>() {

		override val initialState: Resource<Int>
			get() = success(4)

		override suspend fun fetchValue(): Resource<Int> {
			delay(3000)
			return success(2)
		}
	}
}