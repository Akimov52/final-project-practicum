package com.practicum.playlist_maker_android_akimovilya

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import java.util.Locale

enum class PlaylistScreen(val route: String) {
    MAIN("main"),
    SEARCH("search"),
    SETTINGS("settings")
}

@Composable
fun PlaylistHost(navController: NavHostController) {
    val context = LocalContext.current

    val toastTemplate = stringResource(R.string.toast_button_pressed)
    val playlistsTitle = stringResource(R.string.menu_playlists)
    val favoritesTitle = stringResource(R.string.menu_favorites)

    val navigateToSearch: () -> Unit = {
        navController.navigate(PlaylistScreen.SEARCH.route) {
            launchSingleTop = true
        }
    }

    val navigateToSettings: () -> Unit = {
        navController.navigate(PlaylistScreen.SETTINGS.route) {
            launchSingleTop = true
        }
    }

    val navigateBack: () -> Unit = {
        navController.popBackStack()
    }

    NavHost(
        navController = navController,
        startDestination = PlaylistScreen.MAIN.route
    ) {
        composable(PlaylistScreen.MAIN.route) {
            MainScreen(
                onSearchClick = navigateToSearch,
                onPlaylistClick = {
                    Toast.makeText(
                        context,
                        String.format(Locale.getDefault(), toastTemplate, playlistsTitle),
                        Toast.LENGTH_SHORT
                    ).show()
                },
                onFavoritesClick = {
                    Toast.makeText(
                        context,
                        String.format(Locale.getDefault(), toastTemplate, favoritesTitle),
                        Toast.LENGTH_SHORT
                    ).show()
                },
                onSettingsClick = navigateToSettings
            )
        }

        composable(PlaylistScreen.SEARCH.route) {
            SearchScreen(onBackClick = navigateBack)
        }

        composable(PlaylistScreen.SETTINGS.route) {
            SettingsScreen(onBackClick = navigateBack)
        }
    }
}
