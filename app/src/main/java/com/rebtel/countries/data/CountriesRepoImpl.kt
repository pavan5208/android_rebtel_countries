package com.rebtel.countries.data

import com.rebtel.countries.domain.CountriesRepo
import com.rebtel.countries.domain.model.CountriesListResponse
import com.rebtel.countries.domain.model.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class CountriesRepoImpl @Inject constructor(
    private val apiService: CountriesAPIService,
) : CountriesRepo {
    override suspend fun fetchCountriesResults(localResponse: String?): NetworkResult<CountriesListResponse?> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val response = apiService.fetchCountriesList()

                if (response?.size ?: 0 > 0) {

                    NetworkResult.Success(response)
                } else {
//                        val error: String = response?.errorInfo?.info ?: "Something went wrong"
                    NetworkResult.Error(Exception(""))
                }
            } catch (e: Exception) {
                Timber.e(e)
                NetworkResult.Error(e)
            }
        }

}