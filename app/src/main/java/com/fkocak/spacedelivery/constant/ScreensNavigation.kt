package com.fkocak.spacedelivery.constant

import androidx.annotation.DrawableRes
import com.fkocak.spacedelivery.R

//==================================================================================================
/**
 * Sealed Class for Navigation Controller Route Screen. This Class has 3 params for screens..
 * @route, @title, @icon
 */
//==============================================================================================
sealed class ScreensNavigation(val routes:String,val title:String = "",@DrawableRes val icon: Int = R.drawable.ic_space_logo) {

    object SPLASH : ScreensNavigation("splash")
    object CREATE_SPACE_SHIP : ScreensNavigation("create_space_ship")
    object NAVIGATION_VIEW_SCREEN : ScreensNavigation("navigation_screen_view")
    object STATION_SCREEN : ScreensNavigation("station_screen", title = "İstasyon", icon = R.drawable.ic_station)
    object FAVORITE_SCREEN : ScreensNavigation("favorite_screen", title = "Favoriler", icon = R.drawable.ic_favorite)

}