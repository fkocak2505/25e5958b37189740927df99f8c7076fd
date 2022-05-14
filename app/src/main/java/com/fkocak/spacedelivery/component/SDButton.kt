package com.fkocak.spacedelivery.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.fkocak.spacedelivery.component.text.SDText
import com.fkocak.spacedelivery.ui.theme.LOGO_DARK_BLUE
import com.fkocak.spacedelivery.ui.theme.MARGIN_15
import com.fkocak.spacedelivery.ui.theme.TEXT_SIZE_14
import com.fkocak.spacedelivery.ui.theme.TypeOfFont
import com.fkocak.spacedelivery.utils.stateVals.sButonText

@Composable
fun SDButton(
    modifier: Modifier,
    onButtonClickListener: () -> Unit
) {

    Button(
        onClick = { onButtonClickListener.invoke() },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(backgroundColor = LOGO_DARK_BLUE)
    ) {
        SDText(
            text = sButonText,
            style = TypeOfFont.poppinsMediumStyle(TEXT_SIZE_14, TextAlign.Center),
            maxLine = 1,
            color = Color.White,
            modifier = Modifier.padding(start = MARGIN_15, end = MARGIN_15)
        )
    }

}