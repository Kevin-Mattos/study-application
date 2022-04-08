package com.example.datasource.services

import com.example.datasource.Resource
import com.example.datasource.models.response.CepResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface SampleCepService {
	@GET("/wsss/{cep}/json")
	suspend fun getCep(@Path("cep") cep: String): Resource<CepResponse>
}