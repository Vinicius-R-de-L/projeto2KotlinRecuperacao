package com.example.projeto05new.views.genero

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.projeto05new.data.daos.GeneroDao
import com.example.projeto05new.data.models.Genero
import kotlinx.coroutines.launch

class GeneroViewModel(private val dao: GeneroDao): ViewModel() {
    var generos: LiveData<List<Genero>> = dao.getAll().asLiveData()

    fun insert(genero: Genero){
        viewModelScope.launch {
            dao.insert(genero)
        }
    }

    fun delete(genero: Genero){
        viewModelScope.launch {
            dao.delete(genero)
        }
    }

    fun findByGenero(genero: String) : Genero{
        return dao.findByGenero(genero)
    }

}
