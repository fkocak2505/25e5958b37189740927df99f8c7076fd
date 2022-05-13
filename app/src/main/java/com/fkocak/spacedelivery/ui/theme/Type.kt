package com.fkocak.spacedelivery.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.fkocak.spacedelivery.R

object TypeOfFont {

    fun poppinsMediumStyle(textUnit: TextUnit, align: TextAlign? = TextAlign.Start): TextStyle {
        return Typography(
            h1 = TextStyle(
                fontFamily = PoppinsMedium,
                fontSize = textUnit,
                textAlign = align
            )
        ).h1
    }

    fun poppinsSemiBoldStyle(textUnit: TextUnit, align: TextAlign? = TextAlign.Start): TextStyle {
        return Typography(
            h1 = TextStyle(
                fontFamily = PoppinsSemiBold,
                fontSize = textUnit,
                textAlign = align
            )
        ).h1
    }

    fun poppinsRegularStyle(textUnit: TextUnit, align: TextAlign? = TextAlign.Start): TextStyle {
        return Typography(
            h1 = TextStyle(
                fontFamily = PoppinsRegular,
                fontSize = textUnit,
                textAlign = align
            )
        ).h1
    }
}

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
)

val PoppinsMedium = FontFamily(
    Font(R.font.poppins_medium)
)

val PoppinsSemiBold = FontFamily(
    Font(R.font.poppins_semi_bold)
)

val PoppinsRegular = FontFamily(
    Font(R.font.poppins_regular)
)