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

}