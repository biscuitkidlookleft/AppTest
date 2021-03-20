package com.biscuitkid.apptest.di

import com.biscuitkid.apptest.viewmodel.UserViewModel
import org.koin.dsl.module

val appModule = module {
    single { UserViewModel() }
}