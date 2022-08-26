package com.habbashmahmood.clean_note_app.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.habbashmahmood.clean_note_app.SplashScreen
import com.habbashmahmood.clean_note_app.feature_note.presentation.add_edit_note.AddEditNoteScreen
import com.habbashmahmood.clean_note_app.feature_note.presentation.notes.NotesScreen
import com.habbashmahmood.clean_note_app.feature_note.presentation.search_note.SearchScreen
import com.habbashmahmood.clean_note_app.feature_settings.presentation.components.settings.SettingsScreen
import com.habbashmahmood.clean_note_app.core.setup.navigation.Screen
import com.habbashmahmood.clean_note_app.ui.theme.CleanArchitectureNoteAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CleanArchitectureNoteAppTheme {
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    val systemUiController = rememberSystemUiController()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.SplashScreen.route
                    ) {
                        composable(route = Screen.SplashScreen.route) {
                            SplashScreen(
                                navController = navController
                            )
                        }
                        composable(route = Screen.NotesScreen.route) {
                            NotesScreen(
                                navController = navController,
                                systemUiController = systemUiController
                            )
                        }

                        composable(route = Screen.SearchScreen.route) {
                            SearchScreen(navController = navController)
                        }

                        composable(route = Screen.SettingsScreen.route) {
                            SettingsScreen(navController = navController)
                        }

                        composable(
                            route = Screen.AddEditNoteScreen.route +
                                    "?noteId={noteId}&noteColor={noteColor}",
                            arguments = listOf(
                                navArgument(
                                    name = "noteId"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                                navArgument(
                                    name = "noteColor"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                            )
                        ) {
                            AddEditNoteScreen(
                                navController = navController,
                                systemUiController = systemUiController,
                            )
                        }
                    }
                }
            }
        }
    }
}
