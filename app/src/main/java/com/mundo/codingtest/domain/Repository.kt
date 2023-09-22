package com.mundo.codingtest.domain

import com.mundo.codingtest.data.model.Country

interface Repository {
    suspend fun getCountries():List<Country>?
}