package com.fkocak.spacedelivery.views.spaceShip

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.fkocak.spacedelivery.component.text.SDText
import com.fkocak.spacedelivery.component.textField.SDTextField
import com.fkocak.spacedelivery.component.toolbar.SDToolbar
import com.fkocak.spacedelivery.ui.theme.*
import com.fkocak.spacedelivery.utils.stateVals.sTotalScore

@Composable
fun CreateSpaceShipScreenView() {

    val context = LocalContext.current

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(BACKGROUND_HALF_WHITE)
    ) {

        val (toolbar) = createRefs()
        val (tTotalScore) = createRefs()
        val (tfShipName) = createRefs()

        SDToolbar(
            text = "Uzay Aracı Oluştur",
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
            text = "Dağıtılacak Puan: $sTotalScore",
            style = TypeOfFont.poppinsMediumStyle(TEXT_SIZE_16, TextAlign.Start),
            maxLine = 1,
            color = TEXT_COLOR_BLACK,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(tTotalScore) {
                    top.linkTo(toolbar.bottom, MARGIN_15)
                    start.linkTo(parent.start, MARGIN_15)
                }
        )

        SDTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = MARGIN_15, end = MARGIN_15)
                .border(BORDER_1, Color.LightGray, CircleShape)
                .constrainAs(tfShipName) {
                    top.linkTo(tTotalScore.bottom, margin = MARGIN_10)
                    start.linkTo(parent.start, margin = MARGIN_10)
                    end.linkTo(parent.end, margin = MARGIN_10)
                })

    }
}