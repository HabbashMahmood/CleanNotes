package com.habbashmahmood.clean_note_app.feature_settings.presentation.components.settings

import android.widget.Toast
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@ExperimentalAnimationApi
@Composable
fun SettingsScreen(
    navController: NavController,
) {
    val scaffoldState = rememberScaffoldState()
    val mContext = LocalContext.current


    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .height(64.dp)
                    .background(MaterialTheme.colors.primary)
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "My Note",
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.onPrimary,
                    )
                }
                Column {
                    Row {
                        IconButton(
                            onClick = {
                                Toast.makeText(mContext, "Save", Toast.LENGTH_LONG).show()
                            },
                        ) {
                            Icon(
                                imageVector = Icons.Default.Save,
                                contentDescription = "Save",
                                tint = MaterialTheme.colors.onPrimary,
                            )
                        }
                    }
                }

            }

            Row(
                modifier = Modifier
                    .background(MaterialTheme.colors.primary)
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = "Settings Screen")
            }
        }
    }
}
