package me.ashutoshkk.stonks.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import me.ashutoshkk.stonks.presentation.ui.company.CompanyScreen
import me.ashutoshkk.stonks.presentation.ui.home.HomeScreen
import me.ashutoshkk.stonks.presentation.ui.theme.StonksAppTheme


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StonksAppTheme {
                App()
            }
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    val navigateTo = { route: String ->
        navController.navigate(route)
    }
    NavHost(navController = navController, startDestination = Screen.Home.route){
        composable(route = Screen.Home.route){
            HomeScreen(navigateTo)
        }
        composable(route = Screen.Company.route){
            CompanyScreen()
        }
    }
}