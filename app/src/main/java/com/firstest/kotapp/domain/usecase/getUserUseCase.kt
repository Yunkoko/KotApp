package com.firstest.kotapp.domain.usecase

import com.firstest.kotapp.data.repository.UserRepository
import com.firstest.kotapp.domain.entity.User

class getUserUseCase(private val userRepository: UserRepository) {
    suspend fun invoke(email: String) : User{
        return userRepository.getUser(email)
    }
}