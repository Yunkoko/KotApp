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

class MainViewModel(private val createUserUseCase: CreateUserUseCase, private val getUserUseCase: getUserUseCase) : ViewModel(){

    val counter: MutableLiveData<Int> = MutableLiveData()

    init {
        counter.value = 0
    }

    fun onClickIncrement(emailUser: String){
        viewModelScope.launch(Dispatchers.IO) {
            createUserUseCase.invoke(User("test"))
            delay(1000)
            val user = getUserUseCase.invoke("test")
        }
    }
}