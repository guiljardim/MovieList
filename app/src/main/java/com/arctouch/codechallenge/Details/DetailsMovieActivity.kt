package com.arctouch.codechallenge.Details

import android.os.Bundle
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.base.BaseActivity
import com.arctouch.codechallenge.model.Movie
import com.arctouch.codechallenge.util.MovieImageUrlBuilder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details_movie.*


class DetailsMovieActivity : BaseActivity(), DetailsMovieInterface.View {


    private val movieImageUrlBuilder = MovieImageUrlBuilder()
    lateinit var detailMoviePresenter: DetailsMoviePresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_movie)
        detailMoviePresenter = DetailsMoviePresenter(this)
        detailMoviePresenter.onCreateCalled()

    }

    override fun receiveId(): Long {
        val bundle = intent.extras
        val id = bundle.getInt("id")
        return id.toLong()
    }

    override fun createDetailScreen(movie:Movie)
    {
        createImageBackdrop(movie.backdropPath.toString())
        createImagePoster(movie.posterPath.toString())

        movie_title_tv.text = movie.title
        movie_overview_tv.text = movie.overview
        movie_release_date_tv.text = movie.releaseDate
        movie_genre_tv.text = movie.genres?.joinToString(separator = ", ") { it.name }

    }

    private fun createImageBackdrop(backdroPpath: String){
        val image = movieImageUrlBuilder.buildBackdropUrl(backdroPpath)
        Picasso
                .with(this)
                .load(image)
                .placeholder(R.drawable.placeholder)
                .fit()
                .centerInside()
                .into(movie_back_drop_path)


    }

    private fun createImagePoster(posterPath: String){
        val posterImage = movieImageUrlBuilder.buildPosterUrl(posterPath)
        Picasso
                .with(this)
                .load(posterImage)
                .into(movie_cover)

    }



}



