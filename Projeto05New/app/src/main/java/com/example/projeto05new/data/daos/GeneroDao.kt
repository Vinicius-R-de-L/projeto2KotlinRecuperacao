package com.example.projeto05new.data.daos

import androidx.room.*
import com.example.projeto05new.data.models.Genero
import kotlinx.coroutines.flow.Flow

@Dao
interface GeneroDao {
    @Query("SELECT * FROM genero")
    fun getAll(): Flow<List<Genero>>

    @Query("SELECT * FROM genero WHERE gid IN (:generosIds)")
    fun loadAllByIds(generosIds: IntArray): List<Genero>

    @Query("SELECT * FROM genero WHERE genero_musical LIKE :first LIMIT 1")
    fun findByGenero(first: String) : Genero

    @Insert
    suspend fun insert(genero:Genero)

    @Delete
    suspend fun delete(genero:Genero)
}