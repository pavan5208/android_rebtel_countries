package com.rebtel.countries.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.rebtel.countries.R
import com.rebtel.countries.domain.model.CountriesListResponseItem
import com.rebtel.countries.ui.CountriesViewModel
import com.rebtel.countries.ui.adapters.CountriesListAdapter
import com.rebtel.countries.utils.isNetworkAvailable
import com.rebtel.countries.utils.remove
import com.rebtel.countries.utils.show
import com.rebtel.countries.utils.toast
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_countries_list.*
import kotlinx.android.synthetic.main.layout_zero_case.*

class CountriesListFragment : DaggerFragment() {

    private val countriesListViewModel: CountriesViewModel by viewModels({
        requireActivity()
    })

    private val countriesAdapter by lazy { CountriesListAdapter(::onCountrySelected) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_countries_list, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        observeViewModel()
    }

    private fun observeViewModel() {
        countriesListViewModel.apply {
            isLoading.observe(viewLifecycleOwner, {
                it?.let {
                    showOrHideLoading(it)
                }
            })

            responseDataLD.observe(viewLifecycleOwner, {
                if (!it.isNullOrEmpty()) {
                    countriesAdapter.setData(it)
                    showOrHideContent(true)
                    hideEmptyView()
                    showOrHideLoading(false)
                } else {
                    showOrHideContent(false)
                    showEmptyView()
                }
            })

            error.observe(viewLifecycleOwner, {
                it?.let {
                    showErrorView(it)
                }
            })
        }
    }

    private fun showErrorView(it: String) {
        var errorMSg = ""
        if (!requireContext().isNetworkAvailable()) {
            errorMSg = resources.getString(R.string.network_error)
        } else if (!it.isNullOrBlank()) {
            errorMSg = it
        } else {
            errorMSg = resources.getString(R.string.something_wrong)
        }
        showOrHideContent(false)
        tv_zero_case?.text = errorMSg
        btn_retry?.show()
        empty_layout?.show()
        btn_retry?.setOnClickListener {
            btn_retry?.remove()
            empty_layout?.remove()
            fetchData()
        }

    }


    private fun showOrHideLoading(status: Boolean) {
        if (status) {
            progress_circular?.show()
        } else {
            progress_circular?.remove()
        }
    }

    private fun showOrHideContent(status: Boolean) {
        if (status) {
            cl_search?.show()
            rv_countries?.show()
        } else {
            rv_countries?.remove()
        }
    }

    private fun showEmptyView() {
        tv_zero_case?.text = resources.getString(R.string.no_data)
        btn_retry?.remove()
        empty_layout?.show()
    }

    private fun hideEmptyView() {
        empty_layout?.remove()
    }

    private fun setUpViews() {
        rv_countries?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = countriesAdapter
        }
        initClicks()
    }

    private fun initClicks() {
        et_search?.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s?.trim()?.length?:0>0 && !countriesListViewModel.countriesListHolder.isNullOrEmpty()) {
                    countriesListViewModel.searchDebounced(s.toString())
                }else{
                    countriesListViewModel.resetSearch()
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

        iv_clear?.setOnClickListener {
            et_search?.clearFocus()
            et_search?.setText("")
        }
        view?.findViewById<View>(R.id.im_back_country)?.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun fetchData() {
        countriesListViewModel.fetchCountriesListData()
    }

    fun onCountrySelected(countryModel: CountriesListResponseItem) {
        countriesListViewModel.setSelectedCountry(countryModel)
        addCountryDetailsFragment()
    }


    private fun addCountryDetailsFragment() {
        val fragmentTransaction: FragmentTransaction? =
            activity?.supportFragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.fragment_container_view, CountryDetailsFragment())
        fragmentTransaction?.addToBackStack(CountryDetailsFragment::class.simpleName)
        fragmentTransaction?.commit()
    }
}