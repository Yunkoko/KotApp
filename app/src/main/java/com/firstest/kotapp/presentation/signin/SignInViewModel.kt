package com.firstest.kotapp.presentation.signin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.firstest.kotapp.domain.entity.User
import com.firstest.kotapp.domain.usecase.CreateUserUseCase
import com.firstest.kotapp.domain.usecase.getUserUseCase
import com.firstest.kotapp.presentation.login.SignInError
import com.firstest.kotapp.presentation.login.SignInStatus
import com.firstest.kotapp.presentation.login.SignInSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignInViewModel(private val createUserUseCase: CreateUserUseCase, private val getUserUseCase: getUserUseCase) : ViewModel(){

    val signInLiveData: MutableLiveData<SignInStatus> = MutableLiveData()

    fun onClickSignIn(username: String, password: String){
        viewModelScope.launch(Dispatchers.IO) {
            val userCheck = getUserUseCase.invoke(username, password)
            val signInStatus = if(username != "" && password != "" && userCheck == null){
                val user = User(username,password)
                createUserUseCase.invoke(user)
                SignInSuccess(username, password)
            } else {
                SignInError
            }
            withContext(Dispatchers.Main){
                signInLiveData.value = signInStatus
            }
        }
    }
}