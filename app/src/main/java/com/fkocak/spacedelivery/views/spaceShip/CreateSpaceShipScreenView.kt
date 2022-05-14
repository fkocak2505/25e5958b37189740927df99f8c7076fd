package com.fkocak.spacedelivery.views.spaceShip

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.fkocak.SpaceDeliveryProgressBar
import com.fkocak.spacedelivery.component.SDButton
import com.fkocak.spacedelivery.component.slider.SDSlider
import com.fkocak.spacedelivery.component.text.SDText
import com.fkocak.spacedelivery.component.textField.SDTextField
import com.fkocak.spacedelivery.component.toolbar.SDToolbar
import com.fkocak.spacedelivery.constant.*
import com.fkocak.spacedelivery.data.model.Response4Stations
import com.fkocak.spacedelivery.data.model.ShipInfo
import com.fkocak.spacedelivery.ui.theme.*
import com.fkocak.spacedelivery.utils.ApiStateView
import com.fkocak.spacedelivery.utils.stateVals.*
import com.fkocak.spacedelivery.vm.StationsVM

@Composable
fun CreateSpaceShipScreenView(
    onGoNextScreenView: () -> Unit
) {

    val context = LocalContext.current

    val stationsVM: StationsVM = hiltViewModel()

    prepareVMListener(stationsVM)

    checkIfSavedShipInfoSavedBefore()

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(BACKGROUND_HALF_WHITE)
    ) {

        val (toolbar) = createRefs()
        val (tTotalScore) = createRefs()
        val (tfShipName) = createRefs()
        val (sDurability, sSpeed, sCapacity) = createRefs()
        val (bGoAnotherView) = createRefs()

        SDToolbar(
            text = "Uzay Aracı Oluştur",
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

        SDText(
            text = "Dağıtılacak Puan: ${calculateTotalScore()} ${if (calculateTotalScore() == 0) "✅" else ""}",
            style = TypeOfFont.poppinsMediumStyle(TEXT_SIZE_16, TextAlign.Start),
            maxLine = 1,
            color = TEXT_COLOR_BLACK,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(tTotalScore) {
                    top.linkTo(toolbar.bottom, MARGIN_15)
                    start.linkTo(parent.start, MARGIN_15)
                }
        )

        SDTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = MARGIN_15, end = MARGIN_15)
                .border(BORDER_1, Color.LightGray, CircleShape)
                .constrainAs(tfShipName) {
                    top.linkTo(tTotalScore.bottom, margin = MARGIN_10)
                    start.linkTo(parent.start, margin = MARGIN_10)
                    end.linkTo(parent.end, margin = MARGIN_10)
                })

        SDSlider(
            valueRange = sShipDurabilityValueRange,
            value = sShipDurability,
            enabled = sDurabilityIsEnabled,
            text = "Dayanıklılık",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = MARGIN_15, end = MARGIN_15)
                .constrainAs(sDurability) {
                    top.linkTo(tfShipName.bottom, MARGIN_10)
                    start.linkTo(parent.start, MARGIN_15)
                    end.linkTo(parent.end, MARGIN_15)
                },
            {
                sShipDurability = it
            }, {
                changeOtherSlideRange(0)
                sSpeedIsEnabled = true
            }
        )

        SDSlider(
            valueRange = sShipSpeedValueRange,
            value = sShipSpeed,
            enabled = sSpeedIsEnabled,
            text = "Hız",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = MARGIN_15, end = MARGIN_15)
                .constrainAs(sSpeed) {
                    top.linkTo(sDurability.bottom, MARGIN_20)
                    start.linkTo(parent.start, MARGIN_15)
                    end.linkTo(parent.end, MARGIN_15)
                },
            {
                sShipSpeed = it
            }, {
                changeOtherSlideRange(1)
                sCapacityIsEnabled = true
            }
        )

        SDSlider(
            valueRange = sShipCapacityValueRange,
            value = sShipCapacity,
            enabled = sCapacityIsEnabled,
            text = "Kapasite",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = MARGIN_15, end = MARGIN_15)
                .constrainAs(sCapacity) {
                    top.linkTo(sSpeed.bottom, MARGIN_20)
                    start.linkTo(parent.start, MARGIN_15)
                    end.linkTo(parent.end, MARGIN_15)
                },
            {
                sShipCapacity = it
            }, {
            }
        )

        SDButton(
            modifier = Modifier
                .constrainAs(bGoAnotherView) {
                    top.linkTo(sCapacity.bottom, MARGIN_20)
                    start.linkTo(parent.start, MARGIN_15)
                    end.linkTo(parent.end, MARGIN_15)
                }
        ) {
            checkAllObligatoryField(
                stationsVM = stationsVM,
                context = context,
                onGoNextScreenView = onGoNextScreenView
            )
        }

    }
}

private fun checkAllObligatoryField(
    stationsVM: StationsVM,
    context: Context,
    onGoNextScreenView: () -> Unit
) {
    when {
        sShipName.isEmpty() -> Toast.makeText(
            context,
            "Uzay araç ismi boş bırakılamaz..!",
            Toast.LENGTH_SHORT
        ).show()
        calculateTotalScore() != 0 -> Toast.makeText(
            context,
            "Dayanıklılık, Hız ve Malzeme Kapasitesi toplamı 15 olmalıdır. Şu anda ${sumOfAllObligatoryFieldsValue()}",
            Toast.LENGTH_SHORT
        ).show()
        else -> {
            stationsVM.saveSpaceShipInfo(
                shipName = sShipName,
                durability = sShipDurability,
                speed = sShipSpeed,
                capacity = sShipCapacity,
                damageCapacity = DAMAGE_CAPACITY
            )

            onGoNextScreenView.invoke()
        }
    }
}

@Composable
private fun prepareVMListener(stationsVM: StationsVM) {

    val context = LocalContext.current

    when (stationsVM.resultOfInsertShipsInfo.value) {
        is ApiStateView.Success -> {
            Toast.makeText(
                context,
                "Uzay aracınızın bilgileri başarılı bir şekilde kaydedildi..",
                Toast.LENGTH_SHORT
            ).show()

        }
        is ApiStateView.Error -> {
            val msg = ((stationsVM.resultOfInsertShipsInfo.value) as ApiStateView.Error).error
            Toast.makeText(
                context,
                "Uzay aracınızın bilgileri kaydedilirken bir hata oluştu..!",
                Toast.LENGTH_SHORT
            ).show()
        }
        else -> {}
    }

    when (stationsVM.shipInfoState.value) {
        is ApiStateView.Success -> {
            val response =
                ((stationsVM.shipInfoState.value) as ApiStateView.Success).any as ShipInfo

            Toast.makeText(
                context,
                response.shipname,
                Toast.LENGTH_SHORT
            ).show()
        }
        is ApiStateView.Error -> {
            val msg = ((stationsVM.shipInfoState.value) as ApiStateView.Error).error
            Toast.makeText(
                context,
                msg,
                Toast.LENGTH_SHORT
            ).show()
        }
        else -> {}
    }
}

private fun changeOtherSlideRange(type: Int) {
    resetSliderRangeValue(type = type)
    resetSliderCurrentValues(type = type)

    val remainingScore = calculateTotalScore()

    calculateSlidersRangeValue(type = type, remainingScore = remainingScore)

}

private fun resetSliderRangeValue(type: Int) {
    sShipCapacityValueRange = RESET_RANGE_START_VALUE_FOR_SLIDER..RESET_RANGE_END_VALUE_FOR_SLIDER
    when (type) {
        0 -> {
            sShipSpeedValueRange =
                RESET_RANGE_START_VALUE_FOR_SLIDER..RESET_RANGE_END_VALUE_FOR_SLIDER
        }
    }
}

private fun resetSliderCurrentValues(type: Int) {
    sShipCapacity = RESET_CURRENT_VALUE_FOR_SLIDER
    when (type) {
        0 -> {
            sShipSpeed = RESET_CURRENT_VALUE_FOR_SLIDER
        }
    }
}

private fun calculateSlidersRangeValue(type: Int, remainingScore: Int) {
    when (type) {
        0 -> {
            val speedRangeValsByFloat = (remainingScore - 1).toFloat()
            val capacityRangeValsByFloat = 1.toFloat()

            sShipSpeedValueRange = 0f..speedRangeValsByFloat
            sShipCapacityValueRange = 0f..capacityRangeValsByFloat
        }
        1 -> {
            val capacityRangeValsByFloat = remainingScore.toFloat()

            sShipCapacityValueRange = 0f..capacityRangeValsByFloat
        }
    }
}

private fun checkIfSavedShipInfoSavedBefore() {
    sShipInfoData?.let {
        sDurabilityIsEnabled = true
        sSpeedIsEnabled = true
        sCapacityIsEnabled = true

        sShipName = it.shipname
        sShipDurability = it.durability
        sShipSpeed = it.speed
        sShipCapacity = it.capacity

        sButonText = "Güncelle"
    }
}