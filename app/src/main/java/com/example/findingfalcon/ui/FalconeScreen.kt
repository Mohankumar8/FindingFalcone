@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.findingfalcon.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.findingfalcon.R
import com.example.findingfalcon.ui.compose.DestinationScreen
import com.example.findingfalcon.ui.compose.ResultScreen
import com.example.findingfalcon.ui.viewmodel.DestinationViewModel
import com.example.findingfalcon.ui.viewmodel.ResultViewModel
import com.example.findingfalcon.ui.viewmodel.SharedViewModel

@Composable
fun FalconeApp(
    modifier: Modifier = Modifier,
    viewModel: DestinationViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel = hiltViewModel(),
    resultViewModel: ResultViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = Screen.valueOf(
        backStackEntry?.destination?.route ?: Screen.Destination1.name
    )
    Scaffold(
        topBar = {
            FalconeAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        val planets by viewModel.planets.collectAsState()
        val vehicles by viewModel.vehicles.collectAsState()

        NavHost(
            navController = navController,
            startDestination = Screen.Destination1.name,
            modifier = modifier.padding(innerPadding)
        ) {
            // Destination 1
            composable(route = Screen.Destination1.name) {
                DestinationScreen(
                    planets,
                    vehicles,
                    onNextButtonClicked = {navController.navigate(Screen.Destination2.name)},
                    viewModel,
                    sharedViewModel
                )
            }
            // Destination 2
            composable(route = Screen.Destination2.name) {
                DestinationScreen(
                    planets,
                    vehicles,
                    onNextButtonClicked = {navController.navigate(Screen.Destination3.name)},
                    viewModel,
                    sharedViewModel
                )
            }
            // Destination 3
            composable(route = Screen.Destination3.name) {
                DestinationScreen(
                    planets,
                    vehicles,
                    onNextButtonClicked = {navController.navigate(Screen.Destination4.name)},
                    viewModel,
                    sharedViewModel
                )
            }
            // Destination 4
            composable(route = Screen.Destination4.name) {
                DestinationScreen(
                    planets,
                    vehicles,
                    onNextButtonClicked = {navController.navigate(Screen.Result.name)},
                    viewModel,
                    sharedViewModel
                )
            }
            // Result Screen
            composable(route = Screen.Result.name) {
                ResultScreen(
                    resultViewModel,
                    sharedViewModel,
                onStartButtonClicked = {
                    navigateToStart(sharedViewModel, navController)
                })
            }
        }
    }
}

/**
 * Resets and pops up to Start
 */
private fun navigateToStart(
    sharedViewModel: SharedViewModel,
    navController: NavHostController
) {
    sharedViewModel.resetApp()
    navController.popBackStack(Screen.Destination1.name, inclusive = false)
}

@Composable
fun FalconeAppBar(
    currentScreen: Screen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(text = stringResource(currentScreen.title),
            color = Color.White
            ) },
        modifier = modifier,
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        navigationIcon = {
            if(canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back),
                        tint = Color.White
                    )
                }
            }
        }
    )
}