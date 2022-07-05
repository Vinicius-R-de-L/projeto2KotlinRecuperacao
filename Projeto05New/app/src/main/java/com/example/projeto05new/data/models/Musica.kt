package com.example.projeto05new.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Musica(
    @PrimaryKey(autoGenerate = true) val id:Int = 0,
    val nome: String?,
    val lancamento: String?,
    val cantor: String?,
    val genero: String?
)