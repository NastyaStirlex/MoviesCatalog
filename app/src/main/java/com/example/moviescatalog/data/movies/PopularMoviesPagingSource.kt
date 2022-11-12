package com.example.moviescatalog.data.movies

import androidx.compose.runtime.mutableStateListOf
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviescatalog.screens.MainScreen.STARTING_PAGE_INDEX
import com.example.moviescatalog.utils.Constants
import retrofit2.HttpException
import java.io.IOException

class PopularMoviesPagingSource(
    private val moviesRepository: MoviesRepository,
) : PagingSource<Int, Movy>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movy> {
        return try {
            val nextPageNumber = params.key ?: STARTING_PAGE_INDEX
            val response = moviesRepository.getPopularMovies(nextPageNumber)
            val responseData = mutableStateListOf<Movy>()
            val data = response.body()?.movies ?: emptyList()
            responseData.addAll(data)

            LoadResult.Page(
                data = responseData,
                prevKey = if (nextPageNumber > 0) nextPageNumber - 1 else null,
                nextKey = if (nextPageNumber < response.body()?.pageInfo?.pageSize!!) nextPageNumber + 1 else null
            )
        } catch (exception: IOException) {
            val error = IOException("Please Check Internet Connection")
            LoadResult.Error(error)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movy>): Int? {
        return null
    }
}