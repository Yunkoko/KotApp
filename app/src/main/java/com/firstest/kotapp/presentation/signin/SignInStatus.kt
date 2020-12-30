package com.firstest.kotapp.presentation.login

sealed class SignInStatus

data class SignInSuccess(val username: String, val password: String) : SignInStatus()
object SignInError : SignInStatus()