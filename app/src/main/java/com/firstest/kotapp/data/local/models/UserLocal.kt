package com.firstest.kotapp.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.firstest.kotapp.domain.entity.User

@Entity
data class UserLocal(
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "password") val password: String
){
    @PrimaryKey(autoGenerate = true) var uid: Int? = null
}

fun User.toData() : UserLocal {
    return UserLocal(username = username, password = password)
}

fun UserLocal.toEntity() : User {
    return User(username = username, password = password)
}