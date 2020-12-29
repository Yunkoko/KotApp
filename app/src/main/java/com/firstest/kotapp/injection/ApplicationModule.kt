package com.firstest.kotapp.injection

import android.content.Context
import androidx.room.Room
import com.firstest.kotapp.data.local.AppDatabase
import com.firstest.kotapp.data.local.DataBaseDao
import com.firstest.kotapp.data.repository.UserRepository
import com.firstest.kotapp.domain.usecase.CreateUserUseCase
import com.firstest.kotapp.domain.usecase.getUserUseCase
import com.firstest.kotapp.presentation.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val presentationModule = module {
    factory { MainViewModel(get(), get()) }
}

val domainModule = module{
    factory { CreateUserUseCase(get()) }
    factory { getUserUseCase(get()) }
}

val dataModule = module{
    single {UserRepository(get())}
    single {createDataBase(androidContext())}
}

fun createDataBase(context: Context): DataBaseDao{
    val appDatabase: AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "database-name"
    ).build()
    return appDatabase.databaseDao()
}