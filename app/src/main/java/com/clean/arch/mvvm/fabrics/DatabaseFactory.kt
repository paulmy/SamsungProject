package com.clean.arch.mvvm.fabrics

import androidx.room.Room
import com.clean.arch.mvvm.data.MovieDatabase
import com.clean.arch.mvvm.data.dao.MovieDao

object DatabaseFactory {

    private var instance: MovieDao? = null

    fun getMovieDao() : MovieDao {
        var repo = instance
        if (repo == null) {
            repo = getRoom().movieDao
            instance = repo
        }
        return repo
    }

    private fun getRoom() = Room.databaseBuilder(
            AppFactory.getInstance(),
            MovieDatabase::class.java,
            "database"
    ).build()
}