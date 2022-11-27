package com.example.coreui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.coreui.base.ComposeStateFragment
import com.example.coreui.base.viewmodel.StateViewModel
import com.example.datasource.Resource
import com.example.datasource.models.response.CepResponse
import com.example.datasource.services.SampleCepService
import org.koin.androidx.viewmodel.ext.android.viewModel

class SampleFragment : ComposeStateFragment<CepResponse>() {
	override val viewModel: SampleStateViewModel by viewModel()

	@Composable
	override fun OnSuccess(data: CepResponse?) {
		Text(text = "teste do kevinho ${data?.logradouro}")
	}
}

class SampleStateViewModel(private val service: SampleCepService) :
	StateViewModel<CepResponse>() {

	override suspend fun fetchValue(): Resource<CepResponse> {
		return service.getCep("65900-850")
	}
}