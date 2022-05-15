package com.fkocak.spacedelivery.component.horizontalStationList

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.fkocak.spacedelivery.component.horizontalStationListItem.SDHorizontalStationListItem
import com.fkocak.spacedelivery.data.model.Stations
import com.fkocak.spacedelivery.utils.stateVals.sTravellableStationList

@Composable
fun SDHorizontalStationList(
    navController: NavHostController,
    modifier: Modifier
) {

    LazyRow(
        modifier = modifier
    ) {
        itemsIndexed(items = sTravellableStationList,
            key = { index, item ->
                item.name!!
            }
        ) { rowIndex, rowItem ->

            SDHorizontalStationListItem(
                navController = navController,
                items = rowItem,
                modifier = Modifier
                    .width(200.dp)
                    .height(300.dp)
                    .padding(16.dp)
            )
        }
    }

}