package com.jetpackprojects.simpleconvert

import androidx.navigation.NavHostController
import com.jetpackprojects.simpleconvert.DestinationToUnits.AREA
import com.jetpackprojects.simpleconvert.DestinationToUnits.LENGTH
import com.jetpackprojects.simpleconvert.DestinationToUnits.TEMPERATURE
import com.jetpackprojects.simpleconvert.DestinationToUnits.VOLUME
import com.jetpackprojects.simpleconvert.DestinationToUnits.WEIGHT

object DestinationToUnits{
    const val LENGTH="Length"
    const val AREA="Area"
    const val TEMPERATURE="Temperature"
    const val VOLUME="Volume"
    const val WEIGHT="Weight"

}
class AppNavigationActions(private val navController:NavHostController){

    fun navigateToLength(){
        navController.navigate(LENGTH){
            popUpTo(LENGTH)
        }
    }

   fun navigateToArea(){
       navController.navigate(AREA){
           launchSingleTop=true
           restoreState=true
       }
   }
    fun navigateToTemperature(){
        navController.navigate(TEMPERATURE){
            launchSingleTop=true
            restoreState=true
        }
    }
    fun navigateToVolume(){
        navController.navigate(VOLUME){
            launchSingleTop=true
            restoreState=true
        }
    }
    fun navigateToWeight(){
        navController.navigate(WEIGHT){
            launchSingleTop=true
            restoreState=true
        }
    }

}

