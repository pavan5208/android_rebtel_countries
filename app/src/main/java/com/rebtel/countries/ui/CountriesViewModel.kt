package com.rebtel.countries.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rebtel.countries.domain.CountriesRepo
import com.rebtel.countries.domain.model.CountriesListResponse
import com.rebtel.countries.domain.model.CountriesListResponseItem
import com.rebtel.countries.domain.model.NetworkResult
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class CountriesViewModel @Inject constructor(
    private val countriesRepo: CountriesRepo
) : ViewModel() {

    private val _isLoading by lazy { MutableLiveData<Boolean>() }
    val isLoading: LiveData<Boolean> by lazy { _isLoading }

    private val _error by lazy { MutableLiveData<String>() }
    val error: LiveData<String> by lazy { _error }

    private val _responseDataLD by lazy { MutableLiveData<ArrayList<CountriesListResponseItem>?>() }
    val responseDataLD by lazy { _responseDataLD }

    var countriesListHolder: CountriesListResponse? = null

    private val _responseCountryDataLD by lazy { MutableLiveData<CountriesListResponseItem>() }
    val responseCountryDataLD by lazy { _responseCountryDataLD }

    fun fetchCountriesListData() {
        if (_isLoading.value == true) {
            return
        }
        setLoadingState(true)
        viewModelScope.launch {
            countriesRepo.fetchCountriesResults(null).let {
                onResponseReceived(it)
            }
        }
    }

    private fun onResponseReceived(result: NetworkResult<CountriesListResponse?>) {
        if (result is NetworkResult.Success) {
            countriesListHolder = result.data
            _responseDataLD.postValue(result.data)
        } else if (result is NetworkResult.Error) {
            _error.postValue(result.exception.message ?: "Something went wrong")
        }
        setLoadingState(false)
    }

    fun setLoadingState(state: Boolean) {
        _isLoading.postValue(state)
    }

    /**
     * To search the list we are doing simple debounce operation
     * as there are few 100 records from the API response
     */
    private var searchJob: Job? = null
    fun searchDebounced(searchText: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(300)
            filterList(searchText)
        }
    }

    fun resetSearch() {
        searchJob?.cancel()
        _responseDataLD.postValue(countriesListHolder)
    }

    /**
     * For filtering the list we are taking 3 priorities
     * 1. Filter the given word with country name that has startWith
     * 2. Filter the given word with country name with Contains if not in step 1 above
     * 3. Filter the given word with country nativeName or cioc(short form) contains if not in step 1 & step 2 above
     */
    fun filterList(text: String) {
        if (!countriesListHolder.isNullOrEmpty()) {
            val searchList = countriesListHolder?.filter {
                it.name != null && it.name.startsWith(text, ignoreCase = true)
            }?.toCollection(ArrayList())

            countriesListHolder?.filter {
                it.name != null && it.name.contains(text, ignoreCase = true)
            }?.toCollection(ArrayList())?.let {
                it.forEach {
                    if (searchList?.contains(it) != true) {
                        searchList?.add(it)
                    }
                }
            }
            countriesListHolder?.filter {
                (it.nativeName != null && it.cioc != null) && (it.nativeName!!.contains(
                    text,
                    ignoreCase = true
                ) || it.cioc!!.contains(text, ignoreCase = true))
            }?.toCollection(ArrayList())?.let {
                it.forEach {
                    if (searchList?.contains(it) != true) {
                        searchList?.add(it)
                    }
                }
            }
            if (!searchList.isNullOrEmpty()) {
                _responseDataLD.postValue(searchList)
            } else {
                _responseDataLD.postValue((arrayListOf<CountriesListResponseItem>() as? CountriesListResponse))
            }
        } else {
            _responseDataLD.postValue((arrayListOf<CountriesListResponseItem>() as? CountriesListResponse))
        }
    }

    /** We are not making an API call here because the details the API returns are similar to fetched countries list info.
     * If needed we can make an API call as above  but the end point and response will change
     * So I have used details from prefetched countries list response
     */
    fun setSelectedCountry(countryModel: CountriesListResponseItem) {
        _responseCountryDataLD.postValue(countryModel)
    }


}