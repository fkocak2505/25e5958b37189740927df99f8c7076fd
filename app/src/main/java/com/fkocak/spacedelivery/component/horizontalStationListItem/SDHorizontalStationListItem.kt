package com.fkocak.spacedelivery.component.horizontalStationListItem

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.fkocak.spacedelivery.component.SDButton
import com.fkocak.spacedelivery.component.favoriteButton.SDFavoriteButton
import com.fkocak.spacedelivery.component.text.SDText
import com.fkocak.spacedelivery.data.model.Response4Stations
import com.fkocak.spacedelivery.ui.theme.*

@Composable
fun SDHorizontalStationListItem(
    items: Response4Stations,
    modifier: Modifier
) {

    val context = LocalContext.current

    Card(
        modifier = modifier,
        backgroundColor = LOGO_DARK_BLUE,
        elevation = 8.dp,
        shape = RoundedCornerShape(16.dp)
    ) {

        ConstraintLayout {

            val (tCapacity, tStock, tNeed, tName) = createRefs()
            val (bTravel, bFavorite) = createRefs()

            SDText(
                text = "Kapasite: ${items.capacity}",
                style = TypeOfFont.poppinsRegularStyle(TEXT_SIZE_10),
                maxLine = 1,
                color = BACKGROUND_HALF_WHITE,
                modifier = Modifier
                    .constrainAs(tCapacity) {
                        top.linkTo(parent.top, MARGIN_10)
                        start.linkTo(parent.start, MARGIN_10)
                    }
            )

            SDText(
                text = "Stok: ${items.stock}",
                style = TypeOfFont.poppinsRegularStyle(TEXT_SIZE_10),
                maxLine = 1,
                color = BACKGROUND_HALF_WHITE,
                modifier = Modifier
                    .constrainAs(tStock) {
                        top.linkTo(tCapacity.bottom)
                        start.linkTo(parent.start, MARGIN_10)
                    }
            )

            SDText(
                text = "İhtiyaç: ${items.need}",
                style = TypeOfFont.poppinsRegularStyle(TEXT_SIZE_10),
                maxLine = 1,
                color = BACKGROUND_HALF_WHITE,
                modifier = Modifier
                    .constrainAs(tNeed) {
                        top.linkTo(tStock.bottom)
                        start.linkTo(parent.start, MARGIN_10)
                    }
            )

            SDText(
                text = "${items.name}",
                style = TypeOfFont.poppinsSemiBoldStyle(TEXT_SIZE_16),
                maxLine = 1,
                color = BACKGROUND_HALF_WHITE,
                modifier = Modifier
                    .constrainAs(tName) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
            )

            SDButton(
                text = "Seyahat Et",
                color = BACKGROUND_HALF_WHITE,
                textColor = LOGO_LIGHT_BLUE,
                style = TypeOfFont.poppinsMediumStyle(TEXT_SIZE_12, TextAlign.Center),
                modifier = Modifier
                    .constrainAs(bTravel) {
                        top.linkTo(tName.bottom, MARGIN_10)
                        start.linkTo(parent.start, MARGIN_15)
                        end.linkTo(parent.end, MARGIN_15)
                        bottom.linkTo(parent.bottom, MARGIN_10)
                    }
            ) {
                Toast.makeText(context, "${items.name}", Toast.LENGTH_SHORT).show()
            }


            SDFavoriteButton(modifier = Modifier
                .padding(6.dp)
                .size(25.dp)
                .constrainAs(bFavorite) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                })


        }
    }
}