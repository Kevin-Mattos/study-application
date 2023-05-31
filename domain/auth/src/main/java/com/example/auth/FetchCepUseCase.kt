package com.example.auth

import com.example.auth.entity.CepData
import com.example.auth.services.SampleCepService

class FetchCepUseCase(private val service: SampleCepService) {

	suspend operator fun invoke(cep: String) = service.getCep(cep).map { CepData(it.logradouro) }
}