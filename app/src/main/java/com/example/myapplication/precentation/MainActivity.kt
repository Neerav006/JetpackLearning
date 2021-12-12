package com.example.myapplication.precentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavArgs
import androidx.navigation.NavArgument
import androidx.navigation.NavArgumentBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.precentation.coindetail.CoinDetail
import com.example.myapplication.precentation.coinlist.CoinList
import com.example.myapplication.precentation.movielist.MovieListItem
import com.example.myapplication.precentation.splash.Splash
import com.example.myapplication.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {

                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "movie_list"
                    ) {


                        composable(route = "splash"){
                            Splash(navController = navController)
                        }

                        composable(route = "movie_list"){
                            MovieListItem()
                        }

                        composable(route = "list_screen") {
                            CoinList(navController)
                        }
                        composable(
                            route = "list_detail" + "/{data}",
                        )
                        {
                            CoinDetail(navController = navController)

                        }

                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}