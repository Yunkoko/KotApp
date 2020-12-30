package com.firstest.kotapp.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.firstest.kotapp.domain.entity.User
import com.firstest.kotapp.domain.usecase.CreateUserUseCase
import com.firstest.kotapp.domain.usecase.getUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val createUserUseCase: CreateUserUseCase, private val getUserUseCase: getUserUseCase) : ViewModel(){

    val loginLiveData: MutableLiveData<LoginStatus> = MutableLiveData()

    fun onClickLogin(username: String, password: String){
        viewModelScope.launch(Dispatchers.IO) {
            val user = getUserUseCase.invoke(username, password)
            val loginStatus = if(user != null && password != null){
                LoginSuccess(user.username, user.password)
            } else {
                LoginError
            }
            withContext(Dispatchers.Main){
                loginLiveData.value = loginStatus
            }
        }
    }
}