package com.roesch.jwsalgaffar.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.roesch.jwsalgaffar.R
import com.roesch.jwsalgaffar.components.SetRow1
import com.roesch.jwsalgaffar.components.SetRow2
import com.roesch.jwsalgaffar.components.SetRow3

@Composable
fun HomeScreen(navController: NavHostController) {
        Box(
            modifier = Modifier.fillMaxSize(),
        )
        {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(60.dp))
                Text(
                    text = "MENU PENGATURAN",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "JWS alGAFFAR",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                )

            Column(
                Modifier
                    .fillMaxHeight(0.8f)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            )
            {
                SetRow1(navController)
                SetRow2(navController)
                SetRow3(navController)
            }

                Row (

                    modifier = Modifier
                        .clickable(
                            onClick = {
                                navController.navigate("Bluetooth")
                                { popUpTo("Bluetooth") { inclusive = true } }
                            }
                        )
                        .fillMaxWidth(0.6f)
                        .padding(horizontal = 10.dp)
                        .background(color = Color.LightGray, shape = RoundedCornerShape(12.dp))
                        .border(
                            width = 1.dp,
                            shape = RoundedCornerShape(12.dp),
                            color = Color(0xff1b1b1b)
                        )
                        .padding(horizontal = 5.dp)
                        .height(50.dp)
                    ,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "KE BLUETOOTH", fontWeight = FontWeight.SemiBold,fontSize = 18.sp, modifier = Modifier.padding(horizontal = 15.dp))
                    Icon(
                        painter = painterResource(id= R.drawable.baseline_settings_bluetooth_24),
                        contentDescription = "Date",
                        Modifier.size(36.dp)
                    )
                }

        }
    }


}
