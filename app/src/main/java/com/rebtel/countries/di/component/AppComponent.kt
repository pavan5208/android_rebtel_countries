package com.rebtel.countries.di.component

import android.app.Application
import com.rebtel.countries.RebtelCountriesApplication
import com.rebtel.countries.di.modules.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class])
interface AppComponent: AndroidInjector<RebtelCountriesApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(app: RebtelCountriesApplication)

}