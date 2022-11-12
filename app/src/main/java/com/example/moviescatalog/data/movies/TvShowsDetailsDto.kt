package com.example.moviescatalog.data.movies

import com.google.gson.annotations.SerializedName

data class TvShowsDetailsDto(
    @SerializedName("tvShow") val tvShow: ResponseMoviesDetails
)

fun TvShowsDetailsDto.toTvShowDetail(): TvShowDetails {
    return TvShowDetails(
        tvShow = ResponseMoviesDetails(
            id = tvShow.id,
            name = tvShow.name,
            poster = tvShow.poster,
            year = tvShow.year,
            country = tvShow.country,
            genres = tvShow.genres,
            reviews = tvShow.reviews,
            time = tvShow.time,
            tagline = tvShow.tagline,
            description = tvShow.description,
            director = tvShow.director,
            budget = tvShow.budget,
            fees = tvShow.fees,
            ageLimit = tvShow.ageLimit
        )
    )
}