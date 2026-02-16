package com.civilunits.canada.navigation

sealed class Route(val route: String) {
    data object Categories : Route("categories")
    data object QuickCivil : Route("quick_civil")
    data object CivilTools : Route("civil_tools")
    data object Favorites : Route("favorites")
    data object History : Route("history")
    data object Settings : Route("settings")
    data class Converter(val categoryId: String = "{categoryId}") : Route("converter/$categoryId") {
        companion object {
            const val ROUTE_PATTERN = "converter/{categoryId}"
            const val ARG_CATEGORY_ID = "categoryId"
        }
    }
}
