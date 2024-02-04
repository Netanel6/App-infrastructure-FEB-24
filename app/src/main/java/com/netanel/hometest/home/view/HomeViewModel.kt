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
        val dataResult = MutableLiveData<Characters?>()

        suspend fun getCharacters() {
            viewModelScope.launch(Dispatchers.IO) {
                when (val result = useCase.invoke()) {
                    is Result.Success -> {
                        dataResult.postValue(result.data)

                        Logger.info("getCharacters success", result.data.toString())
                    }

                    is Result.Error -> {
                        Logger.info("getCharacters error", result.error.toString())
                    }
                }
            }
        }
    }
