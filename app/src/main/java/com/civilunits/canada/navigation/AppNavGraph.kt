package com.civilunits.canada.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.civilunits.canada.ui.categories.CategoriesScreen
import com.civilunits.canada.ui.civiltools.RebarScreen
import com.civilunits.canada.ui.converter.ConverterScreen
import com.civilunits.canada.ui.favorites.FavoritesScreen
import com.civilunits.canada.ui.history.HistoryScreen
import com.civilunits.canada.ui.quickcivil.QuickCivilScreen
import com.civilunits.canada.ui.settings.SettingsScreen

data class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector
)

val bottomNavItems = listOf(
    BottomNavItem(Route.Categories.route, "Categories", Icons.Default.GridView),
    BottomNavItem(Route.QuickCivil.route, "Quick", Icons.Default.Speed),
    BottomNavItem(Route.CivilTools.route, "Tools", Icons.Default.Build),
    BottomNavItem(Route.Favorites.route, "Favorites", Icons.Default.Favorite),
    BottomNavItem(Route.History.route, "History", Icons.Default.History)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val showBottomBar = bottomNavItems.any { it.route == currentDestination?.route }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    bottomNavItems.forEach { item ->
                        NavigationBarItem(
                            icon = { Icon(item.icon, contentDescription = item.label) },
                            label = { Text(item.label) },
                            selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                            onClick = {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Route.Categories.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Route.Categories.route) {
                CategoriesScreen(
                    onCategoryClick = { categoryId ->
                        navController.navigate("converter/$categoryId")
                    },
                    onSettingsClick = {
                        navController.navigate(Route.Settings.route)
                    }
                )
            }

            composable(Route.Settings.route) {
                SettingsScreen(
                    onBack = { navController.popBackStack() }
                )
            }

            composable(Route.QuickCivil.route) {
                QuickCivilScreen()
            }

            composable(Route.CivilTools.route) {
                RebarScreen()
            }

            composable(Route.Favorites.route) {
                FavoritesScreen(
                    onFavoriteClick = { categoryId ->
                        navController.navigate("converter/$categoryId")
                    }
                )
            }

            composable(Route.History.route) {
                HistoryScreen(
                    onEntryClick = { categoryId ->
                        navController.navigate("converter/$categoryId")
                    }
                )
            }

            composable(
                route = Route.Converter.ROUTE_PATTERN,
                arguments = listOf(
                    navArgument(Route.Converter.ARG_CATEGORY_ID) {
                        type = NavType.StringType
                    }
                )
            ) {
                ConverterScreen(
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}
