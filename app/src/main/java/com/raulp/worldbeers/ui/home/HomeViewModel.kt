package com.raulp.worldbeers.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raulp.worldbeers.data.models.Beer
import com.raulp.worldbeers.data.models.ResponseStatus
import com.raulp.worldbeers.data.models.toBeer
import com.raulp.worldbeers.data.repository.BeerRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * @author Raul Palade
 * @date 20/01/2023
 * @project WorldBeers
 */

class HomeViewModel(private val beerRepository: BeerRepository) : ViewModel() {
    private var _beers = MutableLiveData<ResponseStatus<List<Beer>>>()
    val beers: LiveData<ResponseStatus<List<Beer>>>
        get() = _beers

    private var beerList = listOf<Beer>()

    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        viewModelScope.launch(Dispatchers.Main) {
            _beers.postValue(ResponseStatus.Failure("Exception handled: ${throwable.localizedMessage}"))
        }
    }
    private val coroutineContext: CoroutineContext
        get() = SupervisorJob() + Dispatchers.IO

    fun getBeerList() {
        job = CoroutineScope(coroutineContext).launch(exceptionHandler) {
            val response = beerRepository.getAllBeers()
            withContext(Dispatchers.Main) {
                when (response) {
                    is ResponseStatus.Success -> {
                        beerList = response.value.body()?.map { it.toBeer() }.orEmpty()
                        _beers.postValue(ResponseStatus.Success(beerList))
                    }
                    is ResponseStatus.Failure -> {
                        _beers.postValue(ResponseStatus.Failure("Unable to retrieve beer list"))
                    }
                }
            }
        }
    }

    fun filterBeers(parameter: String) {
        if (parameter.isNotEmpty()) {
            val filteredBeers =
                beerList.filter { beer ->
                    beer.name.contains(parameter, ignoreCase = true) ||
                            beer.description.contains(parameter, ignoreCase = true)
                }
            _beers.postValue(ResponseStatus.Success(filteredBeers))
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}