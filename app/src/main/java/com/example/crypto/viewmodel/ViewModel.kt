package com.example.crypto.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.crypto.local.CryptoDataBase
import com.example.crypto.pojo.CryptoResponseItem
import com.example.crypto.remote.CryptoRetrofit
import com.example.crypto.remote.RepositoryCrypto
import kotlinx.coroutines.launch

class ViewModel(application: Application):AndroidViewModel(application) {
    private val repository:RepositoryCrypto
    val cryptoLiveDataFromBase : LiveData<List<CryptoResponseItem>>
    init {
        val dao = CryptoDataBase.getDataBase(application).getCryptoDao()
        repository= RepositoryCrypto(dao)
        viewModelScope.launch {
            repository.getGameWithCoroutines()
        }
        cryptoLiveDataFromBase = repository.liveDataCryptoDB
    }


}