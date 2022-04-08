package com.example.datasource.environment

sealed class Environment(val url: String) {

	object Env : Environment("https://viacep.com.br")

}