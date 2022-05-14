package com.fkocak.spacedelivery.component.slider

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.fkocak.spacedelivery.component.text.SDText
import com.fkocak.spacedelivery.ui.theme.*
import kotlin.math.roundToInt

@Composable
fun SDSlider(
    valueRange: ClosedFloatingPointRange<Float>,
    value: Int,
    enabled: Boolean,
    text: String,
    modifier: Modifier,
    onValueChange: (Int) -> Unit,
    onValueChanged: () -> Unit
) {
//    var sliderPosition by remember { mutableStateOf(valueRange.start) }

    Column(
        modifier = modifier
    ) {
        Slider(
            value = value.toFloat(),
            onValueChange = {
                onValueChange.invoke(it.roundToInt())
            },
            enabled = enabled,
            valueRange = valueRange,
            onValueChangeFinished = {
                onValueChanged.invoke()
            },
            steps = if (valueRange.endInclusive.toInt() == 0) 0 else valueRange.endInclusive.toInt() - 1,
            colors = SliderDefaults.colors(
                thumbColor = LOGO_DARK_BLUE,
                activeTrackColor = LOGO_LIGHT_BLUE,
                inactiveTrackColor = TEXT_COLOR_GREY
            )
        )

        if (enabled) {
            Spacer(modifier = Modifier.height(4.dp))

            SDText(
                text = "$text: $value",
                style = TypeOfFont.poppinsRegularStyle(TEXT_SIZE_14, TextAlign.Start),
                maxLine = 1,
                color = TEXT_COLOR_BLACK,
                modifier = Modifier)
        }

    }
}