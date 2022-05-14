package com.fkocak.spacedelivery.component.horizontalStationList

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fkocak.spacedelivery.component.horizontalStationListItem.SDHorizontalStationListItem
import com.fkocak.spacedelivery.utils.stateVals.sAllStationData

@Composable
fun SDHorizontalStationList(
    modifier: Modifier
) {

    LazyRow(
        modifier = modifier
    ) {
        itemsIndexed(items = sAllStationData,
            key = { index, item ->
                item.stationId!!
            }
        ) { rowIndex, rowItem ->

            SDHorizontalStationListItem(
                items = rowItem,
                modifier = Modifier
                    .width(200.dp)
                    .height(200.dp)
                    .padding(16.dp)
            )
        }
    }

}