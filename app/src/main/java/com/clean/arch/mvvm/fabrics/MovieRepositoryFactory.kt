package com.clean.arch.mvvm.fabrics

import com.clean.arch.mvvm.data.repositories.MovieRepository

object MovieRepositoryFactory {

    private var instance: MovieRepository? = null

    fun getMovieRepository() : MovieRepository {
        var repo = instance
        if (repo == null) {
            repo = MovieRepository(ApiFactory.getApi(), DatabaseFactory.getMovieDao())
            instance = repo
        }
        return repo
    }
}