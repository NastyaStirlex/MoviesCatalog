package com.example.moviescatalog.screens.MainScreen.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviescatalog.data.di.ApiService
import com.example.moviescatalog.data.toMovie
import com.example.moviescatalog.screens.MainScreen.models.MovieModel
import retrofit2.HttpException
import javax.inject.Inject

class MoviesPagingSource @Inject constructor(
    private val apiService: ApiService
) : PagingSource<Int, MovieModel>() {
    override fun getRefreshKey(state: PagingState<Int, MovieModel>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        //movieRepository.getMoviesPage(params.key ?: 1)
        val page = params.key ?: 1
        val response = apiService.getMoviesPage(params.key ?: 1)

        return if(response.isSuccessful) {
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