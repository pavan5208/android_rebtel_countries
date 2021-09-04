package com.rebtel.countries.di.modules

import com.rebtel.countries.ui.activities.CountriesListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @ContributesAndroidInjector(
        modules = [
            FragmentBindingModule::class
        ]
    )
    abstract fun injectCountriesListActivity(): CountriesListActivity


}