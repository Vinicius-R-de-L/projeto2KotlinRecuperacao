package com.example.projeto05new.data.daos

import androidx.room.*
import com.example.projeto05new.data.models.Musica
import kotlinx.coroutines.flow.Flow

@Dao
interface MusicaDao {
    @Query("SELECT * FROM musica")
    fun getAll(): Flow<List<Musica>>

    @Query("SELECT * FROM musica WHERE id IN (:musicasId)")
    fun loadAllByIds(musicasId: IntArray): List<Musica>

    @Query("SELECT * FROM musica WHERE nome LIKE :first LIMIT 1")
    fun findByName(first: String) : Musica

    @Insert
    suspend fun insert(musica:Musica)

    @Delete
    suspend fun delete(musica:Musica)


}