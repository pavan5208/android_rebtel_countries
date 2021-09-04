package com.rebtel.countries.ui.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.rebtel.countries.R
import com.rebtel.countries.ui.CountriesViewModel
import dagger.android.support.DaggerAppCompatActivity
import java.lang.ref.WeakReference
import javax.inject.Inject

class CountriesListActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val countriesListViewModel: CountriesViewModel by viewModels {
        viewModelFactory
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Data fetch gets started before fragment is getting ready
        countriesListViewModel.fetchCountriesListData()
    }

    companion object {
        fun startActivity(activityWeakReference: WeakReference<Activity>) {
            val intent = Intent(activityWeakReference.get(), CountriesListActivity::class.java)
            activityWeakReference.get()?.startActivity(intent)
        }
    }
}