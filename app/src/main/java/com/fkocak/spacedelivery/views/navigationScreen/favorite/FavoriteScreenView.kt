package com.fkocak.spacedelivery.views.navigationScreen.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.fkocak.spacedelivery.component.toolbar.SDToolbar
import com.fkocak.spacedelivery.data.model.Response4Stations
import com.fkocak.spacedelivery.ui.theme.*
import com.fkocak.spacedelivery.utils.stateVals.sAllStationData

@Composable
fun FavoriteScreenView() {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(BACKGROUND_HALF_WHITE)
            .padding(bottom = MARGIN_56)
    ) {

        val (toolbar) = createRefs()
        val (lcAllStationData) = createRefs()

        SDToolbar(
            text = "Favoriler",
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

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = MARGIN_100)
                .constrainAs(lcAllStationData) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
        ) {
            itemsIndexed(items = sAllStationData,
                key = { index, item ->
                    item.stationId!!
                }
            ) { rowIndex, rowItem ->
                drawElement(rowItem)
            }
        }

    }
}

@Composable
fun drawElement(rowItem: Response4Stations) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        Row {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = rowItem.name!!, style = typography.h6)
                Text(text = rowItem.stock.toString(), style = typography.caption)
            }
        }
    }
}