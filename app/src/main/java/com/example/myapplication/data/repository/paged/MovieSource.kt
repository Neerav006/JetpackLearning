package com.example.myapplication.data.repository.paged

import androidx.compose.runtime.collectAsState
import androidx.paging.LoadState
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.myapplication.data.model.Movie
import com.example.myapplication.domain.repository.MovieRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import retrofit2.HttpException
import java.io.IOException

class MovieSource(
    private val movieRepository: MovieRepository
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val nextPage = params.key ?: 1
            val movieListResponse = movieRepository.getMovieList(nextPage.toString())

            LoadResult.Page(
                movieListResponse.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (movieListResponse.results.isEmpty()) null else movieListResponse.page+1
            )
        } catch (e: IOException) {
           return LoadResult.Error(e)
        }
        catch (e:HttpException){
           return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }
}