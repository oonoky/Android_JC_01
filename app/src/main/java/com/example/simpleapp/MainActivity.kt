package com.mkrdeveloper.lazyrowonclickexamplejetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mkrdeveloper.lazyrowonclickexamplejetpack.screens.DetailScreen
import com.mkrdeveloper.lazyrowonclickexamplejetpack.screens.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Using MaterialTheme directly without the custom theme
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val imageId = arrayOf(
                        R.drawable.p1,
                        R.drawable.p2,
                        R.drawable.p3,
                        R.drawable.p4,
                        R.drawable.p5,
                        R.drawable.p6
                    )

                    val names = arrayOf(
                        "Пепперони",
                        "Веганская",
                        "Четыре сыра",
                        "Маргарита",
                        "Американская",
                        "Мексиканская"
                    )

                    val ingredients = arrayOf(
                        "Томатный соус, сыр, орегано, пепперони",
                        "Томатный соус, сыр, орегано, шпинат, зелёный перец, руккола",
                        "Томатный соус, орегано, моцарелла, гауда, пармезан, чеддер",
                        "Томатный соус, сыр, орегано, базилик",
                        "Томатный соус, сыр, орегано, зелёный перец, красная фасоль",
                        "Томатный соус, сыр, орегано, кукуруза, халапеньо, курица",
                    )

                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "MainScreen") {
                        composable(route = "MainScreen") {
                            MainScreen(imageId, names, ingredients, navController)
                        }
                        composable(route = "DetailScreen/{index}",
                            arguments = listOf(
                                navArgument(name = "index") {
                                    type = NavType.IntType
                                }
                            )
                        ) { backStackEntry ->
                            DetailScreen(
                                photos = imageId,
                                names = names,
                                ingredients = ingredients,
                                itemIndex = backStackEntry.arguments?.getInt("index") ?: 0
                            )
                        }
                    }
                }
            }
        }
    }
}