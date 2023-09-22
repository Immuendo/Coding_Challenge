package com.mundo.codingtest.data.api

import com.mundo.codingtest.data.model.Countries
import retrofit2.Response
import retrofit2.http.GET

interface CountryService {

    companion object {
        const val COUNTRY_ENDPOINT = "/peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/countries.json"
    }

    @GET(COUNTRY_ENDPOINT)
    suspend fun getCountries(): Response<Countries>
}