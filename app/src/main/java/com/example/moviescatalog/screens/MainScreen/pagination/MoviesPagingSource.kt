package com.example.moviescatalog.screens.MainScreen.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviescatalog.data.di.ApiService
import com.example.moviescatalog.data.models.Movie
import com.example.moviescatalog.data.toMovie
import retrofit2.HttpException
import javax.inject.Inject

class MoviesPagingSource @Inject constructor(
    private val apiService: ApiService
) : PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        //movieRepository.getMoviesPage(params.key ?: 1)
        val page = params.key ?: 1
        val response = apiService.getMoviesPage(params.key ?: 1)

        return if (response.isSuccessful) {
            val data = checkNotNull(response.body()).movies.map {
                it.toMovie()
            }
            val pageSize = checkNotNull(response.body()).pageInfo.pageSize
            val nextKey = if (data.size < pageSize) null else page + 1
            val prevKey = if (page == 1) null else page - 1

            LoadResult.Page(data, prevKey, nextKey)
        } else {
            LoadResult.Error(HttpException(response))
        }

    }

}