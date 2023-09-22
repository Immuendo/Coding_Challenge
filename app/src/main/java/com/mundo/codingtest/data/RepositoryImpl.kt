package com.mundo.codingtest.data

import android.util.Log
import com.mundo.codingtest.data.api.CountryService
import com.mundo.codingtest.data.model.Country
import com.mundo.codingtest.domain.Repository

class RepositoryImpl(private val service: CountryService): Repository {

    override suspend fun getCountries(): List<Country> {
        lateinit var countryList: List<Country>
        try {
            val response = service.getCountries()
            val body = response.body()
            if(body != null)
                countryList = body
        } catch (exception: Exception){
            Log.i("COUNTRY_APP", exception.message.toString())
        }
        return countryList
    }
}