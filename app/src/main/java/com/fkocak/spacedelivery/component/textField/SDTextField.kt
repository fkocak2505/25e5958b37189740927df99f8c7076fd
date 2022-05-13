package com.fkocak.spacedelivery.component.textField

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import com.fkocak.spacedelivery.component.text.SDText
import com.fkocak.spacedelivery.ui.theme.BACKGROUND_HALF_WHITE
import com.fkocak.spacedelivery.ui.theme.TEXT_COLOR_GREY
import com.fkocak.spacedelivery.ui.theme.TEXT_SIZE_16
import com.fkocak.spacedelivery.ui.theme.TypeOfFont
import com.fkocak.spacedelivery.utils.stateVals.sShipName

@Composable
fun SDTextField(
    modifier: Modifier
) {

    val focusManager = LocalFocusManager.current

    TextField(
        value = sShipName,
        onValueChange = {
            sShipName = it
        },
        placeholder = {
            SDText(
                text = "Uzay araç ismi yazınız",
                style = TypeOfFont.poppinsRegularStyle(TEXT_SIZE_16, TextAlign.Start),
                maxLine = 1,
                color = TEXT_COLOR_GREY,
                modifier = Modifier
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = BACKGROUND_HALF_WHITE,
            disabledTextColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        textStyle = TypeOfFont.poppinsRegularStyle(TEXT_SIZE_16, TextAlign.Start),
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Text
        ),
        modifier = modifier

    )

}