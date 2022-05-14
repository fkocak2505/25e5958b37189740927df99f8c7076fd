package com.fkocak.spacedelivery.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.fkocak.spacedelivery.component.text.SDText
import com.fkocak.spacedelivery.ui.theme.MARGIN_15

@Composable
fun SDButton(
    text: String,
    color: Color,
    textColor: Color,
    style: TextStyle,
    modifier: Modifier,
    onButtonClickListener: () -> Unit
) {

    Button(
        onClick = { onButtonClickListener.invoke() },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(backgroundColor = color)
    ) {
        SDText(
            text = text,
            style = style,
            maxLine = 1,
            color = textColor,
            modifier = Modifier.padding(start = MARGIN_15, end = MARGIN_15)
        )
    }

}