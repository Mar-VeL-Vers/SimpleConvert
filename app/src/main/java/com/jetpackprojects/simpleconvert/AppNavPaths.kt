package com.jetpackprojects.simpleconvert

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jetpackprojects.simpleconvert.area.AreaScreen
import com.jetpackprojects.simpleconvert.length.LengthScreen
import com.jetpackprojects.simpleconvert.temperature.TemperatureScreen
import com.jetpackprojects.simpleconvert.volume.VolumeScreen
import com.jetpackprojects.simpleconvert.weight.WeightScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavPaths(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

) {
    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentNavBackStackEntry?.destination?.route ?: DestinationToUnits.LENGTH
    val navigationActions = remember(navController) {
        AppNavigationActions(navController)
    }
    ModalNavigationDrawer(
        drawerContent = {
            drawerApp(
                route = currentRoute,
                navigateToLength = { navigationActions.navigateToLength() },
                navigateToWeight = { navigationActions.navigateToWeight() },
                navigateToArea = { navigationActions.navigateToArea() },
                navigateToVolume = { navigationActions.navigateToVolume() },
                navigateToTemperature = { navigationActions.navigateToTemperature() },
                closeDrawer = { coroutineScope.launch { drawerState.close() } },
                modifier = Modifier
            )
        },
        drawerState = drawerState
    )
    {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = currentRoute) },
                    modifier = Modifier
                        .fillMaxWidth(),
                    navigationIcon = {
                        IconButton(
                            onClick = { coroutineScope.launch { drawerState.open() } },
                            content = {
                                Icon(imageVector = Icons.Default.Menu, contentDescription = "menu")
                            }
                        )
                    },
                )
            },
            modifier = Modifier
        )
        {
            NavHost(
                navController = navController,
                startDestination = DestinationToUnits.LENGTH,
                modifier = Modifier.padding(it)
            )
            {
                composable(DestinationToUnits.LENGTH) {
                    LengthScreen()
                }
                composable(DestinationToUnits.WEIGHT) {
                    WeightScreen()
                }
                composable(DestinationToUnits.AREA) {
                    AreaScreen()
                }
                composable(DestinationToUnits.VOLUME) {
                    VolumeScreen()
                }
                composable(DestinationToUnits.TEMPERATURE) {
                    TemperatureScreen()
                }
            }
        }
    }
}













































