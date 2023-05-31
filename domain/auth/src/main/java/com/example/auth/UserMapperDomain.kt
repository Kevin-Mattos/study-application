package com.example.auth

data class UserMapperDomain (val id: Int, val name: String)

fun getUserDomain() = UserMapperDomain(2, "nome")