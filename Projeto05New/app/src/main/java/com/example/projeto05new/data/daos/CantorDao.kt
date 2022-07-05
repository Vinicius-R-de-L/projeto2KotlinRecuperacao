package com.example.projeto05new.data.daos

import androidx.room.*
import com.example.projeto05new.data.models.Cantor
import com.example.projeto05new.data.models.Musica
import kotlinx.coroutines.flow.Flow

@Dao
interface CantorDao {
    @Query("SELECT * FROM cantor")
    fun getAll(): Flow<List<Cantor>>

    @Query("SELECT * FROM cantor WHERE uid IN (:cantoresIds)")
    fun loadAllByIds(cantoresIds: IntArray): List<Cantor>

    @Query("SELECT * FROM cantor WHERE cantor.nome = :nome")
    fun findByName(nome: String) : Cantor

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cantor:Cantor)

    @Delete
    suspend fun delete(cantor:Cantor)

    @Query("SELECT * FROM musica WHERE musica.cantor = :primeiroNome")
    fun loadCantorAndMusicNames(primeiroNome: String): List<Musica>
}