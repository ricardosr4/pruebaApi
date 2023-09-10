package com.example.pruebaapi.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pruebaapi.data.remote.model.DogsResponse


class DogViewModel: ViewModel() {

    val dogsModel = MutableLiveData<List<DogsResponse>>()




}