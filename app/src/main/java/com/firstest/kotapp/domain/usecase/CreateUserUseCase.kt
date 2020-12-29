package com.firstest.kotapp.domain.usecase

import com.firstest.kotapp.data.repository.UserRepository
import com.firstest.kotapp.domain.entity.User

class CreateUserUseCase(private val userRepository: UserRepository) {
    suspend fun invoke(user: User){
    userRepository.createUser(user)
    }
}