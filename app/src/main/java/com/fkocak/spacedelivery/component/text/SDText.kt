package com.fkocak.spacedelivery.component.text

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

@Composable
fun SDText(
    text: String,
    style: TextStyle,
    maxLine: Int,
    color: Color,
    modifier: Modifier
) {

    Text(
        text = text,
        modifier = modifier,
        style = style,
        color = color,
        maxLines = maxLine
    )

}