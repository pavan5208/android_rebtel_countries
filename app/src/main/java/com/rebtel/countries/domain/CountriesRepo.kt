package com.rebtel.countries.domain

import com.rebtel.countries.domain.model.CountriesListResponse
import com.rebtel.countries.domain.model.NetworkResult

interface CountriesRepo {

    suspend fun fetchCountriesResults(localResponse: String?): NetworkResult<CountriesListResponse?>

}