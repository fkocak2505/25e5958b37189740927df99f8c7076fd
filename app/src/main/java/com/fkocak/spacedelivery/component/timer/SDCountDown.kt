package com.fkocak.spacedelivery.component.timer

import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.fkocak.spacedelivery.constant.DAMAGE_CAPACITY
import com.fkocak.spacedelivery.constant.calculateRemainingDurabilitySecond
import com.fkocak.spacedelivery.constant.returnWorld
import com.fkocak.spacedelivery.ui.theme.TypeOfFont
import com.fkocak.spacedelivery.utils.stateVals.sRemaningDurabilitySeconds
import com.fkocak.spacedelivery.utils.stateVals.sShipDamageCapacity
import com.fkocak.spacedelivery.utils.stateVals.sTimerStatus
import kotlinx.coroutines.delay
import timber.log.Timber
import java.lang.Math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun SDCountDown(
    navController: NavHostController,
    totalTime: Long,
    handleColor: Color,
    inactiveBarColor: Color,
    activeBarColor: Color,
    modifier: Modifier = Modifier,
) {

    val context = LocalContext.current

    var size by remember {
        mutableStateOf(IntSize.Zero)
    }

    var value by remember {
        mutableStateOf(1f)
    }

    var currentTime by remember {
        mutableStateOf(totalTime)
    }

    Timber.i("CCCC -- $currentTime")

//    sCurrentTime = totalTime

//    val isTimerRunning by remember {
//        mutableStateOf(true)
//    }

    LaunchedEffect(key1 = currentTime, key2 = sTimerStatus) {
        if (currentTime > 0 && sTimerStatus == 1) {
            delay(100L)
            currentTime -= 100L
            value = currentTime / totalTime.toFloat()

            if (currentTime <= 0L) {
                sTimerStatus = 1
                sShipDamageCapacity -= 10
                currentTime = sRemaningDurabilitySeconds.toLong() * 1000L

                if (sShipDamageCapacity <= 0) {
                    sTimerStatus = 2
                    currentTime = 0L
                    sShipDamageCapacity = DAMAGE_CAPACITY
                    Toast.makeText(context, "Zaman Doldu. Dünyaya Dönülüyor..", Toast.LENGTH_SHORT).show()
                    returnWorld()
                    navController.popBackStack()
                }
            }

        }
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .onSizeChanged {
                size = it
            }
    ) {
        Canvas(modifier = modifier) {
            drawArc(
                color = inactiveBarColor,
                startAngle = -215f,
                sweepAngle = 250f,
                useCenter = false,
                size = Size(size.width.toFloat(), size.height.toFloat()),
                style = Stroke(5.dp.toPx(), cap = StrokeCap.Round)
            )

            drawArc(
                color = activeBarColor,
                startAngle = -215f,
                sweepAngle = 250f * value,
                useCenter = false,
                size = Size(size.width.toFloat(), size.height.toFloat()),
                style = Stroke(5.dp.toPx(), cap = StrokeCap.Round)
            )

            val center = Offset(size.width / 2f, size.height / 2f)
            val beta = (250f * value + 145f) * (PI / 180f).toFloat()
            val r = size.width / 2f
            val a = cos(beta) * r
            val b = sin(beta) * r

            drawPoints(
                listOf(Offset(center.x + a, center.y + b)),
                pointMode = PointMode.Points,
                color = handleColor,
                strokeWidth = (5.dp * 3f).toPx(),
                cap = StrokeCap.Round
            )
        }

        Text(
            text = (currentTime / 1000L).toString(),
            style = TypeOfFont.poppinsSemiBoldStyle(22.sp),
            color = Color.White
        )
    }
}