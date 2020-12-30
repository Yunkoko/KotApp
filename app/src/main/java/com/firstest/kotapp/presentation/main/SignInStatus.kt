package com.firstest.kotapp.presentation.main

sealed class SignInStatus

data class SignInSuccess(val username: String, val password: String) : SignInStatus()
object SignInnError : SignInStatus()