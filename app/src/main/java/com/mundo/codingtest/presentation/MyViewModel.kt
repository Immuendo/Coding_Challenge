package com.mundo.codingtest.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.mundo.codingtest.data.model.Country
import com.mundo.codingtest.domain.GetCountriesUseCase


class MyViewModel(
    private val myUseCase: GetCountriesUseCase
): ViewModel() {

    var lastPos: Int? = null

    fun getCountries() = liveData<List<Country>?> {
            val countryList = myUseCase.execute()
            emit(countryList)
        }

}