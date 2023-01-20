package com.raulp.worldbeers.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.raulp.worldbeers.data.repository.BeerRepository

/**
 * @author Raul Palade
 * @date 20/01/2023
 * @project WorldBeers
 */

class HomeViewModelFactory(private val beerRepository: BeerRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            HomeViewModel(beerRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}