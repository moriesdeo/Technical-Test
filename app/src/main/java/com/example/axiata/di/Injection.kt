package com.example.axiata.di

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.example.axiata.viewmodel.ViewModelFactory

object Injection {
    fun provideViewModelFactory(application: Application): ViewModelProvider.Factory {
        return ViewModelFactory(application)
    }
}