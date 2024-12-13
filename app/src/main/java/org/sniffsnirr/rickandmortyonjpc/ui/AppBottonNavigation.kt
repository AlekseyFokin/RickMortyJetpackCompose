package org.sniffsnirr.rickandmortyonjpc.ui

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import org.sniffsnirr.rickandmortyonjpc.NavigationMenuItem
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.currentBackStackEntryAsState
import org.sniffsnirr.rickandmortyonjpc.ui.theme.CommentColor
import org.sniffsnirr.rickandmortyonjpc.ui.theme.FragmentBackground
import org.sniffsnirr.rickandmortyonjpc.ui.theme.TextColor

@Composable
fun AppBottomNavigation(navController: NavController) {// нижнее меню навигации
    val listScreens = listOf(
        NavigationMenuItem.ListCharectersScreen,
        NavigationMenuItem.CharecterScreen,
        NavigationMenuItem.ListLocationsScreen
    )
    NavigationBar(containerColor = FragmentBackground) {
        val backstackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backstackEntry?.destination?.route ?: NavigationMenuItem.SCREEN_1
        listScreens.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                icon = {
                    Icon(
                        painter = painterResource(item.icon),
                        contentDescription = "icon screen"
                    )
                },
                onClick = {navController.navigate(item.route){popUpTo(item.route){inclusive=true} }

                },
                label = { Text(text = item.title) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = TextColor,
                    selectedTextColor = TextColor,
                    unselectedIconColor = CommentColor,
                    unselectedTextColor = CommentColor
                )

            )
        }
    }
}