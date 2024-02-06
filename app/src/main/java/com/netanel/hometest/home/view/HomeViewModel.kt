package com.netanel.hometest.home.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.netanel.hometest.domain.Result
import com.netanel.hometest.home.model.Characters
import com.netanel.hometest.home.useCase.GetCharactersUseCase
import com.netanel.hometest.utils.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by netanelamar on 01/01/24.
 * NetanelCA2@gmail.com
 */

@HiltViewModel
class HomeViewModel
    @Inject
    constructor(private val useCase: GetCharactersUseCase) : ViewModel() {
        private val vmDataResult = MutableLiveData<DataState<Characters?>>()
        val dataResult: MutableLiveData<DataState<Characters?>> by lazy { vmDataResult }

        fun getCharacters() {
            dataResult.postValue(DataState.Loading)
            viewModelScope.launch(Dispatchers.IO) {
                when (val result = useCase.invoke()) {
                    is Result.Success -> {
                        dataResult.postValue(DataState.Success(result.data))
                        Logger.info("getCharacters success", result.data.toString())
                    }

                    is Result.Error -> {
                        dataResult.postValue(DataState.Error(result.error.toString()))
                        Logger.info("getCharacters error", result.error.toString())
                    }
                }
            }
        }
    }

sealed class DataState<out T> {
    data object Loading : DataState<Nothing>()

    data class Success<T>(val data: T) : DataState<T>()

    data class Error(val message: String) : DataState<Nothing>()
}
