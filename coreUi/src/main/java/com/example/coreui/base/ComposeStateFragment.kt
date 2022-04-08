package com.example.coreui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.example.coreui.base.viewmodel.StateViewModel
import com.example.coreui.components.ShimmerAnimation
import com.example.coreui.components.ShimmerItemRow
import com.example.coreui.theme.MyTheme
import com.example.datasource.Resource

abstract class ComposeStateFragment<O> : Fragment() {

	abstract val viewModel: StateViewModel<O>

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		return ComposeView(requireContext()).apply {
			setContent {
				MyTheme {
					ToContent(viewModel = viewModel,
						OnSuccess = { OnSuccess(data = it!!) },
						OnLoading = { OnLoading() },
						OnError = {
							OnError(
								data = null,
								throwable = it
							)
						}
					)
				}
			}
		}
	}

	@Composable
	abstract fun OnSuccess(data: O?)

	//TODO: Make on error open
	@Composable
	abstract fun OnError(data: O?, throwable: Throwable?)

	@Composable
	open fun OnLoading() {
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
	OnSuccess: @Composable (T) -> Unit,
) {

	val screenState by viewModel.stateLiveData.collectAsState()
	Crossfade(targetState = screenState) { state ->
		when (state) {
			is Resource.Error -> OnError(state.throwable)
			is Resource.Loading -> OnLoading(state.data)
			is Resource.Success -> OnSuccess(state.data!!)
		}
	}

}