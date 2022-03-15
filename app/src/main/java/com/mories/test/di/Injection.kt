package com.mories.test.di

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.mories.test.viewmodel.ViewModelFactory

object Injection {
    fun provideViewModelFactory(application: Application): ViewModelProvider.Factory {
        return ViewModelFactory(application)
    }
}