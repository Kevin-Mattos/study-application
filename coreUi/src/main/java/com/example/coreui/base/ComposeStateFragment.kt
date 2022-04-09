package com.example.coreui.base

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.font.FontWeight
import com.example.coreui.base.viewmodel.StateViewModel
import com.example.coreui.components.ShimmerAnimation
import com.example.coreui.components.ShimmerItemRow
import com.example.datasource.Resource

abstract class ComposeStateFragment<O> : ComposeFragment() {

	abstract val viewModel: StateViewModel<O>

	@Composable
	override fun ToContent() {
		ToContent(viewModel = viewModel,
			OnSuccess = { OnSuccess(data = it!!) },
			OnLoading = {
				OnLoading(it)
			},
			OnError = {
				OnError(
					data = null,
					throwable = it
				)
			}
		)
	}

	@Composable
	abstract fun OnSuccess(data: O?)

	//TODO: Make on error open
	@Composable
	open fun OnError(data: O?, throwable: Throwable?) {
		LazyColumn {
			item {
				Text(text = "Ops! Algo deu errado", fontWeight = FontWeight.Bold)
			}
			item {
				Button(onClick = { viewModel.tryAgain() }) {
					Text(text = "Tentar Novamente")
				}
			}
		}
	}

	@Composable
	open fun OnLoading(data: O?) {
		DisplayLoadingStateScreen()
	}
}

@Composable
fun DisplayLoadingStateScreen() {
	// TODO: find better way to show shimmer rows
	LazyColumn {
		items(30) {
			ShimmerAnimation {
				ShimmerItemRow(brush = it)
			}
		}
	}
}

@Composable
fun <T> ToContent(
	viewModel: StateViewModel<T>,
	OnLoading: @Composable (T?) -> Unit,
	OnError: @Composable (Throwable?) -> Unit,
	OnSuccess: @Composable (T?) -> Unit,
) {

	val screenState by viewModel.stateLiveData.collectAsState()
	Crossfade(targetState = screenState) { state ->
		when (state) {
			is Resource.Error -> OnError(state.throwable)
			is Resource.Loading -> OnLoading(state.data)
			is Resource.Success -> OnSuccess(state.data)
		}
	}

}