package com.clean.arch.mvvm.ui.popular

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.clean.arch.mvvm.R
import com.clean.arch.mvvm.data.entities.Movie
import com.clean.arch.mvvm.ui.adapter.MovieAdapter
import com.clean.arch.mvvm.ui.movie.MovieFragment
import com.clean.arch.mvvm.ui.movie.MovieViewModelFactory


class MoviePopularFragment : Fragment(R.layout.recycler_fragment) {


    private val viewModel: MoviePopularViewModel by viewModels()

    private val adapter = MovieAdapter(::onItemClick)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler)
        recyclerView.adapter = adapter
        
        viewModel.moviePage.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    private fun onItemClick(movie: Movie) {
        parentFragmentManager.beginTransaction()
            .setReorderingAllowed(true)
            .replace(R.id.container, MovieFragment.newInstance(movie))
            .addToBackStack(null)
            .commit()

    }

    companion object {
        fun newInstance() = MoviePopularFragment()
    }

}