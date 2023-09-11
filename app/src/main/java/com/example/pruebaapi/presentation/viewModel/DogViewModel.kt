package com.example.pruebaapi.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.pruebaapi.data.remote.model.DogsResponse
import kotlinx.coroutines.launch


class DogViewModel : ViewModel() {
    var dogsModel = MutableLiveData<List<DogsResponse>>()
    val isloading = MutableLiveData<Boolean>()

    fun getMatches(user_name: String) {
        viewModelScope.launch {
            isloading.postValue(true)
            val result = getMatchesRemoteUseCase.getMatchesFire(user_name)
            if (!result.isNullOrEmpty()) {
                dogsModel.postValue(result)
                isloading.postValue(false)
            }
        }
    }

    
}
