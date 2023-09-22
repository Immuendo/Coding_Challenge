package com.mundo.codingtest.domain

import com.mundo.codingtest.data.model.Country

class GetCountriesUseCase(private val repository: Repository) {

    suspend fun execute(): List<Country>?{
        return repository.getCountries()
    }
}