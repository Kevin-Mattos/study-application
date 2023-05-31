package com.example.auth

data class UserMapperData (val id: Int, val name: String)

fun getUserData() = UserMapperData(2, "nome")