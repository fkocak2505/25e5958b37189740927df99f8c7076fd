package com.fkocak.spacedelivery

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.fkocak.NavigationController
import com.fkocak.spacedelivery.base.BaseComponentActivity
import com.fkocak.spacedelivery.ui.theme.SpaceDeliveryTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseComponentActivity() {


    override val startCompose: @Composable () -> Unit
        get() = { beginComposeUI() }

    @Composable
    fun beginComposeUI() {

        NavigationController()

    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        SpaceDeliveryTheme {
            beginComposeUI()
        }
    }
}