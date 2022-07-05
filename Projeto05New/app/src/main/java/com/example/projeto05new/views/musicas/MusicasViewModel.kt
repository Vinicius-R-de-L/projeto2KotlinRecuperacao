package com.example.projeto05new.views.musicas

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.projeto05new.data.daos.MusicaDao
import com.example.projeto05new.data.models.Musica
import kotlinx.coroutines.launch

class MusicasViewModel(private val dao: MusicaDao): ViewModel() {
    var musicas: LiveData<List<Musica>> = dao.getAll().asLiveData()

    fun insert(musica: Musica){
        viewModelScope.launch {
            dao.insert(musica)
        }
    }

    fun delete(musica: Musica){
        viewModelScope.launch {
            dao.delete(musica)
        }
    }

    fun findByName(nome: String) : Musica{
        return dao.findByName(nome)
    }


}
