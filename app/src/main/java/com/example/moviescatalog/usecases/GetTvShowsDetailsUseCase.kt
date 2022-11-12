package com.example.moviescatalog.usecases

import android.util.Log
import com.example.moviescatalog.data.movies.TvShowDetails
import com.example.moviescatalog.data.movies.TvShowsRepository
import com.example.moviescatalog.data.movies.toTvShowDetail
import com.example.moviescatalog.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetTvShowsDetailsUseCase @Inject constructor(
    private val repository: TvShowsRepository
) {
    operator fun invoke(tvShowId: String): Flow<Resource<TvShowDetails>> = flow {
        try {
            emit(Resource.Loading<TvShowDetails>())
            val tvShow = repository.getTvShowsDetails(tvShowId).toTvShowDetail()
            Log.e("tv", tvShowId)
            emit(Resource.Success<TvShowDetails>(tvShow))
        } catch (e: HttpException) {
            emit(Resource.Error<TvShowDetails>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<TvShowDetails>("Couldn't reach server. Check your internet connection."))
        }
    }
}