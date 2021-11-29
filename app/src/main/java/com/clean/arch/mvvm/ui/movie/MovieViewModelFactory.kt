package com.clean.arch.mvvm.ui.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.clean.arch.mvvm.data.entities.Movie
import com.clean.arch.mvvm.fabrics.MovieRepositoryFactory

class MovieViewModelFactory(

    private val movie: Movie
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        MovieViewModel(MovieRepositoryFactory.getMovieRepository(), movie) as T
}