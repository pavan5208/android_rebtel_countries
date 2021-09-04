package com.rebtel.countries.di.modules

import com.rebtel.countries.ui.fragments.CountriesListFragment
import com.rebtel.countries.ui.fragments.CountryDetailsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {

    @ContributesAndroidInjector
    abstract fun injectCountriesListFragment(): CountriesListFragment

    @ContributesAndroidInjector
    abstract fun injectCountryDetailsFragment(): CountryDetailsFragment

}