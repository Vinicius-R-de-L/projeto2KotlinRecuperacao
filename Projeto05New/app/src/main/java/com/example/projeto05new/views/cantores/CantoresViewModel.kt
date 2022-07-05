package com.example.projeto05new.views.cantores

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.projeto05new.data.daos.CantorDao
import com.example.projeto05new.data.models.Cantor
import com.example.projeto05new.data.models.Musica
import kotlinx.coroutines.launch

class CantoresViewModel(private val dao: CantorDao): ViewModel() {
    var cantores: LiveData<List<Cantor>> = dao.getAll().asLiveData()

    fun insert(cantor: Cantor){
        viewModelScope.launch {
            dao.insert(cantor)
        }
    }

    fun delete(cantor: Cantor){
        viewModelScope.launch {
            dao.delete(cantor)
        }
    }

    fun findByName(nome: String) : Cantor{
        return dao.findByName(nome)
    }

    fun loadCantorAndMusicNames(primeiroNome: String) : List<Musica>{
        return dao.loadCantorAndMusicNames(primeiroNome)
    }


}
