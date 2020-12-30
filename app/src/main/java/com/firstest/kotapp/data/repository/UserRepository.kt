package com.firstest.kotapp.data.repository

import com.firstest.kotapp.data.local.DataBaseDao
import com.firstest.kotapp.data.local.models.UserLocal
import com.firstest.kotapp.data.local.models.toData
import com.firstest.kotapp.data.local.models.toEntity
import com.firstest.kotapp.domain.entity.User

class UserRepository(private val dataBaseDao: DataBaseDao) {
    suspend fun createUser(user: User){
        dataBaseDao.insert(user.toData())
    }

    fun getUser(username: String, password: String): User? {
        val userLocal : UserLocal? = dataBaseDao.findByName(username, password)
        return userLocal?.toEntity()
    }
}