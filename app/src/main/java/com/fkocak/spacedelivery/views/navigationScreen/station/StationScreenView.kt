package com.fkocak.spacedelivery.views.navigationScreen.station

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.fkocak.spacedelivery.component.horizontalStationList.SDHorizontalStationList
import com.fkocak.spacedelivery.component.text.SDText
import com.fkocak.spacedelivery.component.toolbar.SDToolbar
import com.fkocak.spacedelivery.constant.calculateTotalScore
import com.fkocak.spacedelivery.ui.theme.*
import com.fkocak.spacedelivery.utils.stateVals.*
import com.fkocak.spacedelivery.views.navigationScreen.favorite.drawElement

@Composable
fun StationScreenView(navController: NavHostController) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(BACKGROUND_HALF_WHITE)
    ) {

        val (toolbar) = createRefs()
        val (tUGS, tEUS, tDS, tShipName, tDamageCapacity, tEUSInfo, tCurrentStationName) = createRefs()
        val (divider) = createRefs()
        val (lrAllStation) = createRefs()

        SDToolbar(
            text = "İstasyonlar",
            style = TypeOfFont.poppinsSemiBoldStyle(TEXT_SIZE_26, TextAlign.Start),
            maxLine = 1,
            color = TEXT_COLOR_BLACK,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = MARGIN_15)
                .constrainAs(toolbar) {
                    top.linkTo(parent.top, margin = MARGIN_56)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        SDText(
            text = "UGS: $sUGS",
            style = TypeOfFont.poppinsMediumStyle(TEXT_SIZE_14, TextAlign.Start),
            maxLine = 1,
            color = TEXT_COLOR_BLACK,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(tUGS) {
                    top.linkTo(toolbar.bottom, MARGIN_15)
                    start.linkTo(parent.start, MARGIN_15)
                }
        )

        SDText(
            text = "EUS: $sEUS",
            style = TypeOfFont.poppinsMediumStyle(TEXT_SIZE_14, TextAlign.Center),
            maxLine = 1,
            color = TEXT_COLOR_BLACK,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(tEUS) {
                    top.linkTo(toolbar.bottom, MARGIN_15)
                }
        )

        SDText(
            text = "DS: $sDS",
            style = TypeOfFont.poppinsMediumStyle(TEXT_SIZE_14, TextAlign.End),
            maxLine = 1,
            color = TEXT_COLOR_BLACK,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(tDS) {
                    top.linkTo(toolbar.bottom, MARGIN_15)
                    end.linkTo(parent.end, MARGIN_15)
                }
        )

        Divider(
            color = LOGO_LIGHT_BLUE,
            thickness = 2.dp,
            modifier = Modifier
                .padding(all = MARGIN_15)
                .constrainAs(divider) {
                    top.linkTo(tDS.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        SDText(
            text = "Uzay Araç Adı: $sShipName",
            style = TypeOfFont.poppinsRegularStyle(TEXT_SIZE_14, TextAlign.Start),
            maxLine = 1,
            color = TEXT_COLOR_BLACK,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(tShipName) {
                    top.linkTo(divider.bottom, MARGIN_15)
                    start.linkTo(parent.start, MARGIN_10)
                }
        )

        SDText(
            text = "Hasar Kapasitesi: $sShipDamageCapacity",
            style = TypeOfFont.poppinsRegularStyle(TEXT_SIZE_14, TextAlign.Start),
            maxLine = 1,
            color = TEXT_COLOR_BLACK,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(tDamageCapacity) {
                    top.linkTo(tShipName.bottom, MARGIN_3)
                    start.linkTo(parent.start, MARGIN_10)
                }
        )

        SDText(
            text = "Kalan Süre: ${sRemaningDurabilitySeconds}sn",
            style = TypeOfFont.poppinsRegularStyle(TEXT_SIZE_14, TextAlign.Start),
            maxLine = 1,
            color = TEXT_COLOR_BLACK,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(tEUSInfo) {
                    top.linkTo(tDamageCapacity.bottom, MARGIN_3)
                    start.linkTo(parent.start, MARGIN_10)
                }
        )

        SDHorizontalStationList(
            navController = navController,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .constrainAs(lrAllStation) {
                    top.linkTo(tEUSInfo.bottom, MARGIN_15)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        SDText(
            text = "İstasyon: ${sCurrentStationInfo?.name}",
            style = TypeOfFont.poppinsSemiBoldStyle(TEXT_SIZE_20, TextAlign.Center),
            maxLine = 1,
            color = TEXT_COLOR_BLACK,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(tCurrentStationName) {
                    top.linkTo(lrAllStation.bottom, MARGIN_20)
                    start.linkTo(parent.start, MARGIN_10)
                }
        )
    }

}