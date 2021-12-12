package com.example.myapplication.precentation.movielist

import android.text.Layout
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.data.model.Movie
import androidx.compose.ui.res.vectorResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.rememberImagePainter
import com.example.myapplication.common.AppConstants
import com.example.myapplication.common.connectivityState
import com.example.myapplication.precentation.coinlist.CoinListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi


@Composable
fun MovieListItem(viewModel: CoinListViewModel = hiltViewModel()){

    val movies = viewModel.movies
    Scaffold(
        topBar = {
          TopAppBar() {
              Text(text = "Popular movies")
          }
        },
        scaffoldState = rememberScaffoldState()

        ) {
        Column(modifier = Modifier.fillMaxSize()) {

            val lazyPagingData = movies.collectAsLazyPagingItems()
            
            LazyColumn(modifier = Modifier.weight(1f)){
                items(lazyPagingData){
                    movie->

                    MovieItem(movie =movie!! )
                }
            }

           lazyPagingData.apply {
               when{
                   loadState.append is LoadState.Loading -> {
                       LoadingItem()

                   }
                   loadState.refresh is LoadState.Loading-> {
                       LoadingView(modifier = Modifier
                           .fillMaxSize()
                           .align(Alignment.CenterHorizontally))
                   }
                   loadState.append is LoadState.Error->{
                       val e = loadState.append as LoadState.Error
                       Toast.makeText(LocalContext.current,"${e.error.localizedMessage}",Toast.LENGTH_SHORT).show()
                       ErrorItem(lazyPagingItems = this,message = e.error.message?:"", onClickRetry = {retry() })
                   }
                   loadState.refresh is LoadState.Error->{
                       val e = loadState.refresh as LoadState.Error
                       ErrorItem(lazyPagingItems = this, message = e.error.message?:"", onClickRetry = {retry() })
                   }
               }



           }

        }
        
    }

}

@Composable
fun MovieItem(movie: Movie) {
    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
        ) {

        MovieTitle(title = movie.title?:"")
        Spacer(modifier =Modifier.size(16.dp) )
        MovieImage(imageUrl =AppConstants.IMAGE_BASE_URL+movie.backdrop_path,
            modifier = Modifier
                .height(160.dp)
                .fillMaxWidth()
            )
        Spacer(modifier =Modifier.size(24.dp) )
        Divider(Modifier.height(16.dp))
    }
}


@Composable
fun MovieImage(
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    Image(
        painter = rememberImagePainter(data = imageUrl),
        contentDescription = null,
        modifier = modifier,
        contentScale = ContentScale.FillBounds
    )
}

@Composable
fun MovieTitle(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = title,
        maxLines = 2,
        style = MaterialTheme.typography.h6,
        overflow = TextOverflow.Ellipsis
    )
}