package com.firstest.kotapp.injection

import com.firstest.kotapp.MainViewModel
import org.koin.dsl.module

val presentationModule = module {
    factory { MainViewModel() }
}