package com.mundo.codingtest.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mundo.codingtest.domain.GetCountriesUseCase

class MyViewModelFactory(
    private val useCase: GetCountriesUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MyViewModel(useCase) as T
    }
}