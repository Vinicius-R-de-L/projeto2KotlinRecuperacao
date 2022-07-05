package com.example.projeto05new.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.projeto05new.data.daos.CantorDao
import com.example.projeto05new.data.daos.GeneroDao
import com.example.projeto05new.data.daos.MusicaDao
import com.example.projeto05new.data.models.Cantor
import com.example.projeto05new.data.models.Genero
import com.example.projeto05new.data.models.Musica

@Database(entities = [Cantor::class, Musica::class, Genero::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cantorDao() : CantorDao
    abstract fun musicaDao() : MusicaDao
    abstract fun generoDao() : GeneroDao
}