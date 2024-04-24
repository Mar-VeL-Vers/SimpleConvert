package com.jetpackprojects.simpleconvert

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun drawerApp(
    route: String,
    modifier: Modifier = Modifier,
    navigateToLength: () -> Unit = {},
    navigateToArea: () -> Unit = {},
    navigateToTemperature: () -> Unit = {},
    navigateToVolume: () -> Unit = {},
    navigateToWeight: () -> Unit = {},
    closeDrawer: () -> Unit = {}

) {
    ModalDrawerSheet(modifier=Modifier) {
        DrawerHeader(modifier)
        Spacer(modifier = Modifier.height(5.dp))
        NavigationDrawerItem(
            label = {
                    Text(
                        text= stringResource(id = R.string.length),
                        style = MaterialTheme.typography.labelSmall
                    )
            },
            selected = route==DestinationToUnits.LENGTH ,
            onClick = {
                navigateToLength()
                closeDrawer()
            },
            icon = {
                Icon(painter = painterResource(R.drawable.ruller_measure), contentDescription ="length" )
            },
            shape = MaterialTheme.shapes.small
        )

        NavigationDrawerItem(
            label = {
                Text(
                    text= stringResource(id = R.string.weight),
                    style = MaterialTheme.typography.labelSmall
                )
            },
            selected = route==DestinationToUnits.WEIGHT ,
            onClick = {
                navigateToWeight()
                closeDrawer()
            },
            icon = {
                Icon(painter = painterResource(R.drawable.scale_24px), contentDescription ="Weight" )
            },
            shape = MaterialTheme.shapes.small
        )
        NavigationDrawerItem(
            label = {
                Text(
                    text= stringResource(id = R.string.area),
                    style = MaterialTheme.typography.labelSmall
                )
            },
            selected = route==DestinationToUnits.AREA ,
            onClick = {
                navigateToArea()
                closeDrawer()
            },
            icon = {
                Icon(painter = painterResource(R.drawable.triangle_measure), contentDescription ="Area" )
            },
            shape = MaterialTheme.shapes.small
        )
        NavigationDrawerItem(
            label = {
                Text(
                    text= stringResource(id = R.string.volume),
                    style = MaterialTheme.typography.labelSmall
                )
            },
            selected = route==DestinationToUnits.VOLUME ,
            onClick = {
                navigateToVolume()
                closeDrawer()
            },
            icon = {
                Icon(painter = painterResource(R.drawable.volume_cube), contentDescription ="Volume" )
            },
            shape = MaterialTheme.shapes.small
        )
        NavigationDrawerItem(
            label = {
                Text(
                    text= stringResource(id = R.string.temperature),
                    style = MaterialTheme.typography.labelSmall
                )
            },
            selected = route==DestinationToUnits.TEMPERATURE ,
            onClick = {
                navigateToTemperature()
                closeDrawer()
            },
            icon = {
                Icon(painter = painterResource(R.drawable.device_thermostat_24px), contentDescription ="Temperature" )
            },
            shape = MaterialTheme.shapes.small
        )
    }

}

@Composable
fun DrawerHeader(modifier: Modifier) {

    Column(
        modifier= Modifier
            .fillMaxWidth()
            .padding(15.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Icon(painter = painterResource(R.drawable.swap_horizontal_circle_24px), contentDescription =null)

            Spacer(modifier = Modifier.padding(5.dp))

            Text(
                text = "SimpleConvert",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge

            )
        }

    }
}

@Preview
@Composable
fun DrawerHeaderPreview() {
    drawerApp(
        modifier = Modifier,
        route = DestinationToUnits.LENGTH
    )
}