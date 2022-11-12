package com.example.moviescatalog.data.movies

data class TvShowDetailsState (
val isLoading: Boolean = false,
val tvShow: TvShowDetails? = null,
val error: String = ""
        )