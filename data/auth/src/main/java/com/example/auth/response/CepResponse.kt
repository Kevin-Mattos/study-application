package com.example.auth.response

data class CepResponse(
	val cep: String,
	val logradouro: String,
	val complemento: String,
	val bairro: String,
	val localidade: String,
	val uf: String,
	val gia: String,
	val ddd: String,
	val siafi: String,
)
