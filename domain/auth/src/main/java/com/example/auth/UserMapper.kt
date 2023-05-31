package com.example.auth

data class UserMapper (val id: Int, val name: String)

fun getUser() = UserMapper(2, "nome")