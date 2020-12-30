package com.firstest.kotapp.presentation.main

sealed class LoginStatus

data class LoginSuccess(val username: String, val password: String) : LoginStatus()
object LoginError : LoginStatus()
