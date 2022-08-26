package com.habbashmahmood.clean_note_app.core.setup.navigation

sealed class Screen(val route: String) {
    object SplashScreen: Screen("splash_screen")
    object NotesScreen: Screen("notes_screen")
    object AddEditNoteScreen: Screen("add_edit_note_screen")
    object SettingsScreen: Screen("settings_screen")
    object SearchScreen: Screen("search_screen")
}
