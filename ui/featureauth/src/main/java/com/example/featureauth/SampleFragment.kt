package com.example.featureauth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.auth.FetchCepUseCase
import com.example.auth.entity.CepData
import com.example.coreui.base.ComposeStateFragment
import com.example.coreui.base.Resource
import com.example.coreui.base.viewmodel.StateViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SampleFragment : ComposeStateFragment<CepData>() {
	override val viewModel: SampleStateViewModel by viewModel()

	@Composable
	override fun OnSuccess(data: CepData) {
		SampleScreen(data)
	}
}

class SampleStateViewModel(private val getCepUseCase: FetchCepUseCase) :
	StateViewModel<CepData>() {

	override suspend fun fetchValue(): Resource<CepData> {
		return getCepUseCase("65900-850").fold(
			onSuccess = { Resource.success(it) },
			onFailure = { Resource.error(it) })
	}
}


@Composable
fun SampleScreen(data: CepData) {
	Column {
		TopAppBar {
			Icon(
				painter = painterResource(id = org.koin.androidx.compose.R.drawable.abc_btn_check_to_on_mtrl_000),
				contentDescription = null
			)
			Text(data.logradouro)
		}
		Text(text = "text 1")
		LazyColumn(modifier = Modifier.fillMaxWidth()) {
			item {
				Text("texto 2")
			}

			items(listOf(3, 45, 3)) {
				Text("$it")
			}
		}

		Button(onClick = { /*TODO*/ }) {
			Text("Meu botaozinho")
		}

	}
}

@Preview
@Composable
fun Preview() {
	SampleScreen(data = CepData("Logradouro"))
}