package com.example.crypto.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.crypto.local.CryptoDataBase
import com.example.crypto.pojo.CryptoDetailItem
import com.example.crypto.pojo.CryptoResponseItem
import com.example.crypto.remote.CryptoRetrofit
import com.example.crypto.remote.RepositoryCrypto
import kotlinx.coroutines.launch

class ViewModel(application: Application):AndroidViewModel(application) {
    private val repository:RepositoryCrypto
    val cryptoLiveDataFromBase : LiveData<List<CryptoResponseItem>>
    val cryptoSelection = MutableLiveData<CryptoResponseItem>()
    init {
        val dao = CryptoDataBase.getDataBase(application).getCryptoDao()
        repository= RepositoryCrypto(dao)
        //observando el repo con scope
        viewModelScope.launch {
            repository.getGameWithCoroutines()
            repository.getDetailProductWithCourutines()
        }
        cryptoLiveDataFromBase = repository.liveDataCryptoDB
    }

    //metodo para seleccionar el item y pasar a segundo fragmento con todos sus atributos (alternativa a bundle)
    fun selected(cryptoItem: CryptoResponseItem?) {
        cryptoSelection.value = cryptoItem
    }


    //metodo para selecionar un item
    fun selectedItem() : LiveData<CryptoResponseItem> = cryptoSelection
    //metodo para selecionar un item y su detalle by id

    fun getDetailCryptoById(id :String): LiveData<CryptoDetailItem>{
        return repository.getProductDetail(id)

    }

}