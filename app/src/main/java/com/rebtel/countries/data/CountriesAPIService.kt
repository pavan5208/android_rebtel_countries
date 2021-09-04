package com.rebtel.countries.data

import com.rebtel.countries.domain.model.CountriesListResponse
import retrofit2.http.GET

interface CountriesAPIService {
    @GET(COUNTRIES_LIST_REMOTE_PATH)
    suspend fun fetchCountriesList(): CountriesListResponse?

    companion object{
        const val COUNTRIES_LIST_REMOTE_PATH ="/rest/v2/all"
    }
}