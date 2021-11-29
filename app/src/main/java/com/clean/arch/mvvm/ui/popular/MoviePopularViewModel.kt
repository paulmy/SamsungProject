package com.clean.arch.mvvm.ui.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.clean.arch.mvvm.data.repositories.MovieRepository
import com.clean.arch.mvvm.fabrics.MovieRepositoryFactory

class MoviePopularViewModel : ViewModel() {

    private val repository = MovieRepositoryFactory.getMovieRepository()
    val moviePage = repository.moviePopularFlow
            .cachedIn(viewModelScope).asLiveData()
}