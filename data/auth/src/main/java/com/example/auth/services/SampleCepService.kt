package com.example.auth.services

import com.example.auth.response.CepResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface SampleCepService {
	@GET("/ws/{cep}/json")
	suspend fun getCep(@Path("cep") cep: String): Result<CepResponse>
}