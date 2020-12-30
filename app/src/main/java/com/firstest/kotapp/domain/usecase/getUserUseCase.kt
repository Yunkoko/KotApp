package com.firstest.kotapp.domain.usecase

import com.firstest.kotapp.data.repository.UserRepository
import com.firstest.kotapp.domain.entity.User

class getUserUseCase(private val userRepository: UserRepository) {
    suspend fun invoke(username: String, password: String) : User?{
        return userRepository.getUser(username, password)
    }
}