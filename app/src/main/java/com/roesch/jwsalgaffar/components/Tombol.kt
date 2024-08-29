package com.roesch.jwsalgaffar.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun TombolIcon (name: String?, icon: ImageVector, navController: NavController, nav : String) {

    Row (
        modifier = Modifier
            .clickable(
                onClick = { navController.navigate(nav) }
            )
            .fillMaxWidth(0.5f)
            .padding(horizontal = 5.dp)
            .background(color = Color.LightGray, shape = RoundedCornerShape(10.dp))
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(10.dp),
                color = Color(0xff1b1b1b)
            )
            .padding(horizontal = 5.dp)
            .height(50.dp)
        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = name.toString(),
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 15.dp)
        )
        Icon(
            imageVector = icon,
            contentDescription = "Date",
            Modifier.size(40.dp)
        )
    }
}

@Composable
fun TombolKirim() {
    Row (
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .padding(horizontal = 5.dp)
            .background(color = Color.LightGray, shape = RoundedCornerShape(10.dp))
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(10.dp),
                color = Color(0xff1b1b1b)
            )
            .padding(horizontal = 5.dp)
            .height(50.dp)
        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "KIRIM DATA",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 15.dp)
        )
        Icon(
            imageVector = Icons.Default.Email,
            contentDescription = "Date",
            Modifier.size(40.dp)
        )
    }
}
