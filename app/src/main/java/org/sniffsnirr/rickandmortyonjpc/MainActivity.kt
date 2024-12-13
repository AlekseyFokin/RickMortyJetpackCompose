package org.sniffsnirr.rickandmortyonjpc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.sniffsnirr.rickandmortyonjpc.ui.AppBottomNavigation
import org.sniffsnirr.rickandmortyonjpc.ui.CharacterView
import org.sniffsnirr.rickandmortyonjpc.ui.ListCharactersView
import org.sniffsnirr.rickandmortyonjpc.ui.ListLocationsView
import org.sniffsnirr.rickandmortyonjpc.ui.theme.FragmentBackground
import org.sniffsnirr.rickandmortyonjpc.ui.theme.RickAndMortyOnJPCTheme


class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<ApiViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RickAndMortyOnJPCTheme {
                val navController = rememberNavController()
                Scaffold(bottomBar = { AppBottomNavigation(navController) }, containerColor = FragmentBackground)
                {paddingValues ->
                    AppNavigation( modifier = Modifier.padding(
                        bottom = paddingValues.calculateBottomPadding()),
                        navController)
                }
            }
        }
    }

    @Composable
    fun AppNavigation(modifier: Modifier ,navController: NavHostController) {// настройка navHostController
        NavHost(modifier= modifier,navController = navController, startDestination = NavigationMenuItem.SCREEN_1) {
            composable(NavigationMenuItem.SCREEN_1) { ListCharactersView(viewModel, navController) }
            composable(NavigationMenuItem.SCREEN_2) {CharacterView(viewModel) }
            composable(NavigationMenuItem.SCREEN_3) { ListLocationsView(viewModel) }
        }
    }


}



