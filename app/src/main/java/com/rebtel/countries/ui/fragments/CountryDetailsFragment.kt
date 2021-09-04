package com.rebtel.countries.ui.fragments

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.rebtel.countries.R
import com.rebtel.countries.domain.model.CountriesListResponseItem
import com.rebtel.countries.ui.CountriesViewModel
import com.rebtel.countries.utils.Utils
import com.rebtel.countries.utils.glide.GlideHandle
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_country_details.*

class CountryDetailsFragment : DaggerFragment() {

    private val countriesListViewModel: CountriesViewModel by viewModels({
        requireActivity()
    })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_country_details, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        observeViewModel()
    }

    private fun observeViewModel() {
        countriesListViewModel.apply {
            responseCountryDataLD.observe(viewLifecycleOwner, {
                updateUI(it)
            })
        }
    }

    private fun updateUI(countryData: CountriesListResponseItem) {
        GlideHandle.loadSVG(
            im_flag_details,
            countryData.flag ?: "",
            R.drawable.ic_loading
        )
        var data = "\nDetails \n"

        if (!countryData.capital.isNullOrBlank()) {
            data += "\nCapital \n" +
                    countryData.capital
        }
        if (countryData.population != null) {
            data += "\n\nPopulation \n" +
                    countryData.population
        }
        if (!countryData.cioc.isNullOrBlank()) {
            data += "\n\nShort Code \n" +
                    countryData.cioc
        }
        if (!countryData.borders.isNullOrEmpty()) {
            data += "\n\nBorders \n" +
                    Utils.getBoardersData(countryData.borders)
        }
        if (!countryData.currencies.isNullOrEmpty()) {
            data += "\n\nCurrencies \n" +
                    Utils.getCurrencyData(countryData.currencies)
        }
        if (!countryData.languages.isNullOrEmpty()) {
            data += "\n\nLanguages \n" +
                    Utils.getLanguageNames(countryData.languages)+"\n"
        }
        txt_details?.text = data
        txt_details?.movementMethod = ScrollingMovementMethod();

    }

    private fun setUpViews() {
        initClickListeners()
    }

    private fun initClickListeners() {
        view?.findViewById<View>(R.id.im_back_country_details)?.setOnClickListener {
            activity?.onBackPressed()
        }
    }
}