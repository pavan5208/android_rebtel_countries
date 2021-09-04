package com.rebtel.countries.di.modules

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.rebtel.countries.di.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module(includes = [ActivityBindingModule::class,
                    ViewModelBindingModule::class,
                    NetworkModule::class])
abstract class AppModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
    @Binds
    abstract fun bindContext(application: Application): Context
}