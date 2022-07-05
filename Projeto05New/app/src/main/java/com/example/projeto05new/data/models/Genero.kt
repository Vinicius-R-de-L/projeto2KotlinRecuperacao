package com.example.projeto05new.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Genero (
    @PrimaryKey(autoGenerate = true) val gid:Int = 0,
    val genero_musical: String?,
    val descricao: String
)